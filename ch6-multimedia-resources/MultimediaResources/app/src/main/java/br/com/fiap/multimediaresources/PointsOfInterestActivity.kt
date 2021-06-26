package br.com.fiap.multimediaresources

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PointsOfInterestActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_of_interest)

        val mapFragment = supportFragmentManager
                            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    /**
     * Function for managing information on the map
     */
    override fun onMapReady(googleMap: GoogleMap) {

        // link the objects
        mMap = googleMap

        // Point of interest data
        val point_of_interest_information =
            arrayOf("Catavento Cultural",
                "Parque Dom Pedro II Av. Mercúrio, s/n São Paulo - SP")

                // Point of Interest Coordinates
                val point_of_interest_latitude_longitude = LatLng(-23.5440055,-46.629888)

        // Inserts the point of interest data into the map object
        mMap.addMarker(MarkerOptions()
            .position(point_of_interest_latitude_longitude)
            .title(point_of_interest_information[0])
            .snippet(point_of_interest_information[1])
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )

        // Move the camera so that the view appears closest to the unit addresses.
        // The 12.5F float value indicates the distance from the camera which can vary between 0.0F and 21.0F
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point_of_interest_latitude_longitude, 15.5F));

        // MAP_TYPE_NORMAL
        // MAP_TYPE_TERRAIN
        // MAP_TYPE_HYBRID
        // MAP_TYPE_NONE
        // MAP_TYPE_SATELLITE
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Configures the display of information in a custom way
        mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {

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