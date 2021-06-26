package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class UsingMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * This function manipulates the map with all the necessary details for the presentation of information,
     * such as pin colors, title texts and address complements.
     *
     * Fixed latitude and longitude data for FIAP units
     */
    override fun onMapReady(googleMap: GoogleMap) {

        // link the objects
        mMap = googleMap

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

        // Adds the latitude and longitude of FIAP Campus Vila Olimpia
        val fiapVilaOlimpia = LatLng(-23.5955843, -46.6851937)

        // Adds the latitude and longitude of FIAP Campus Paulista
        val fiapPaulista = LatLng(-23.5643721, -46.652857)

        // Adds the latitude and longitude of FIAP Campus Vila Mariana
        val fiapVilaMariana = LatLng(-23.5746685, -46.6232043)

        // Inserts the unit 1 data into the map object.
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapVilaOlimpia)
                .title(unidades[0][0])
                .snippet(unidades[0][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        // Inserts the unit 2 data into the map object.
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapPaulista)
                .title(unidades[1][0])
                .snippet(unidades[1][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )

        // Inserts the unit 3 data into the map object.
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapVilaMariana)
                .title(unidades[2][0])
                .snippet(unidades[2][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        // Move the camera so that the view appears closest to the unit addresses.
        // The 12.5F float value indicates the distance from the camera which can vary between 0.0F and 21.0F
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiapPaulista, 12.5F));

        // Configures the display of titles and addresses of FIAP units in a personalized way
        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {

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

