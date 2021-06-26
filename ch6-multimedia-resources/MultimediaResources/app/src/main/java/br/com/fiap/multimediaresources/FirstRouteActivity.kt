package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.beust.klaxon.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

class FirstRouteActivity : AppCompatActivity(), OnMapReadyCallback {

    /**
     * In this routine we will instantiate a Map with 2 points and draw a route
     * between point1 and point2 consulting the coordinates via Google Maps
     */

    // Object containing the Google Map
    private var mMap: GoogleMap? = null

    // Starting the routine
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_route)

        val mapFragment = supportFragmentManager
                            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    /**
     * Google Maps main method
     */
    override fun onMapReady(googleMap: GoogleMap) {

        // link the objects
        mMap = googleMap

        //-- Prepare an object with latitude and longitude
        val LatLongB = LatLngBounds.Builder()

        // prepares a collection of information from FIAP units. The information will skip a line
        val unidades = arrayOf(
                arrayOf(
                        "FIAP Campus Vila Olimpia",
                        "Rua Olimpíadas,186 São Paulo - SP CEP: 04551-000"
                ),
                arrayOf(
                        "FIAP Campus Paulista",
                        "Av. Paulista,1106 São Paulo - SP CEP: 01311-000"
                ),
                arrayOf(
                        "FIAP Campus Vila Mariana",
                        "Av. Lins de Vasconcelos,1264 São Paulo - SP CEP: 01538-001"
                )
        )

        // Adds the latitude and longitude of FIAP Campus Vila Mariana
        val fiap_campus_vila_mariana = LatLng(-23.5746685, -46.6232043)

        // Adds the latitude and longitude of FIAP Campus Vila Olimpia
        val fiap_campus_vila_olimpia = LatLng(-23.5955843, -46.6851937)

        // Adds the latitude and longitude of IAP Campus Paulista
        val fiap_campus_paulista = LatLng(-23.5643721, -46.652857)

        // Selecting information
        val pointA = fiap_campus_paulista;
        val pointB = fiap_campus_vila_olimpia;

        val unidadePointA = unidades[1]
        val unidadePointB = unidades[0]

        // Point A Unit
        mMap!!.addMarker(
                MarkerOptions()
                        .position(pointA)
                        .title(unidadePointA[0])
                        .snippet(unidadePointB[1])
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        // Point B Unit
        mMap!!.addMarker(
                MarkerOptions()
                        .position(pointB)
                        .title(unidadePointA[0])
                        .snippet(unidadePointB[1])
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        // Programming the route between Point A and Point B
        val url = getURL(pointA, pointB)

        // Processing line color and line width visual information
        val options = PolylineOptions()
        options.color(Color.BLUE)
        options.width(7f)

        // Synchronizing information request with Google Map via INTERNET
        async {

            val result = URL(url).readText()

            uiThread {

                val parser: Parser = Parser()
                val stringBuilder: StringBuilder = StringBuilder(result)
                val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                val routes = json.array<JsonObject>("routes")
                if ((routes?.size ?: 0) <= 0) {
                    return@uiThread
                }

                val points = routes!!["legs"]["steps"][0] as JsonArray<JsonObject>
                val polypts =
                        points.flatMap { decodePoly(it.obj("polyline")?.string("points")!!) }

                // Processing Point A information
                options.add(pointA)
                LatLongB.include(pointA)
                for (point in polypts) {
                    options.add(point)
                    LatLongB.include(point)
                }

                // Processing Point B information
                options.add(pointB)
                LatLongB.include(pointB)
                val bounds = LatLongB.build()

                // Adding the route to the map
                mMap!!.addPolyline(options)

                // Centering the Map
                mMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

                // Configures the display of titles and addresses of FIAP units in a personalized way
                mMap!!.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {

                    override fun getInfoWindow(arg0: Marker): View? {
                        return null
                    }

                    override fun getInfoContents(marker: Marker): View {

                        val info = LinearLayout(applicationContext)
                        info.orientation = LinearLayout.VERTICAL

                        // Title
                        val title = TextView(applicationContext)
                        title.setTextColor(Color.BLACK)
                        title.gravity = Gravity.LEFT
                        title.setTypeface(null, Typeface.BOLD)
                        title.text = marker.title

                        // Complement
                        val snippet = TextView(applicationContext)
                        snippet.setTextColor(Color.GRAY)
                        snippet.text = marker.snippet

                        // Add the title and the complement in the brand
                        info.addView(title)
                        info.addView(snippet)

                        return info
                    }
                })

            }
        }
    }

    // Collecting data from PointA and PointB via URL
    private fun getURL(from: LatLng, to: LatLng): String {

        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        var key: String? = "null"
        key?.let {
            val params = "$origin&$dest&$sensor&key=$it"
            return "https://maps.googleapis.com/maps/api/directions/json?$params"
        }
        throw IllegalArgumentException("CHANGE KEY VALUE")
    }

    // Decoding the points
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(
                    lat.toDouble() / 1E5,
                    lng.toDouble() / 1E5
            )
            poly.add(p)
        }

        return poly
    }
}