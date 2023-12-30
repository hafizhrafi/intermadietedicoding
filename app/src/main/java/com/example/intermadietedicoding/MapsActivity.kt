package com.example.intermadietedicoding

import android.content.Intent
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.intermadietedicoding.databinding.ActivityMapsBinding
import com.example.intermadietedicoding.response.GettAllStoriesHandler

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000

    private var latitude = 0.0
    private var longitude = 0.0
    var listLocation = emptyList<GettAllStoriesHandler>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val i: Intent = intent
        listLocation = i.getSerializableExtra("listLocation") as List<GettAllStoriesHandler>

        listLocation.forEach {
            val latLng = LatLng(it.lat!!.toDouble(), it.lon!!.toDouble())
            mMap.addMarker(MarkerOptions().position(latLng).title(it.name))
        }
        val latLng =
            LatLng(listLocation.last().lat!!.toDouble(), listLocation.last().lon!!.toDouble())

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }
}