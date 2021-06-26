package br.com.fiap.multimediaresources

import java.lang.Double

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class UserLocationActivity : AppCompatActivity(),
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    com.google.android.gms.location.LocationListener, OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationManager: LocationManager? = null
    private var mLocationRequest: LocationRequest? = null
    private var locationManager: LocationManager? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    val isLocationEnabled: Boolean
        get() {
            locationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            ) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

    val UPDATE_INTERVAL = (2 * 10000).toLong()
    val FASTEST_INTERVAL: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_location)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mGoogleApiClient = GoogleApiClient.Builder(this).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this).addApi(LocationServices.API)
            .build()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        checkLocation()
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        }
    }

    override fun onConnectionSuspended(i: Int) {
        mGoogleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}


    override fun onStart() {
        super.onStart()
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }


    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.disconnect()
        }
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {

        mLocationRequest =
            LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL).setFastestInterval(FASTEST_INTERVAL)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
            location?.let {
                val msg = "Updated Your Location:" +
                        Double.toString(location.latitude) + "," +
                        Double.toString(location.longitude)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                val latLng = LatLng(location.latitude, location.longitude)
                updateMap(mMap, latLng)
            } ?: Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onLocationChanged(location: Location) {

    }

    private fun checkLocation(): Boolean {
        return isLocationEnabled
    }

    override fun onMapReady(googleMap: GoogleMap) {

        updateMap(googleMap, null)
    }

    fun updateMap(googleMap: GoogleMap, latLng: LatLng?) {

        // link the objects
        mMap = googleMap
        // Adds the latitude and longitude of FIAP Campus Vila Mariana
        var myTitle = "FIAP Campus Vila Mariana"
        var mySnippet = "Av. Lins de Vasconcelos,1264 São Paulo - SP CEP: 01538-001"
        var myLocation = LatLng(-23.5746685, -46.6232043)
        if (latLng != null) {
            myLocation = latLng
            myTitle = "Location captured"
            mySnippet = "See your location details."
        }
        // Color Range of Pins on Map
        var bitmap = arrayOf(0.0F, 30.0F, 60.0F, 120.0F, 180.0F, 210.0F, 240.0F, 270.0F, 300.0F, 330.0F)
        var bitmapSorted = bitmap[((Math.random() * bitmap.size).toInt())]
        // Insert into map object
        mMap.addMarker(
            MarkerOptions().position(myLocation).title(myTitle).snippet(mySnippet).icon(BitmapDescriptorFactory.defaultMarker(bitmapSorted))
        )
        // Move the camera so that the view appears closest to the unit addresses.
        // The 12.5F float value indicates the distance from the camera which can vary between 0.0F and 21.0F
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                myLocation,
                12.5F
            )
        );
        // Configures the display of titles and addresses of FIAP units in a personalized way
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