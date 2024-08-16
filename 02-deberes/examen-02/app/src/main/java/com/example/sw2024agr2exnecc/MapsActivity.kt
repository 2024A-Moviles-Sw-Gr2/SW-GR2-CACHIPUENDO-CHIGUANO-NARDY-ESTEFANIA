package com.example.sw2024agr2exnecc

import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Request permissions
        solicitarPermisos()

        // Initialize the map fragment and set the callback for when the map is ready
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        // Set up the map directly here once the fragment is ready
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            establecerConfiguracionMapa()

            // Retrieve the data passed via Intent
            val latitude = intent.getDoubleExtra("latitude", 0.0)
            val longitude = intent.getDoubleExtra("longitude", 0.0)
            val locationName = intent.getStringExtra("locationName") ?: "Location"

            // Set a marker on the map based on the passed data
            val location = LatLng(latitude, longitude)
            mMap.addMarker(MarkerOptions().position(location).title(locationName))
            moverCamaraConZoom(location, 15f)
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(nombrePermisoFine, nombrePermisoCoarse),
                1
            )
        }
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mMap) {
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                    permisoCoarse == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mMap.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
                uiSettings.isZoomControlsEnabled = true
            }
        }
    }

    fun moverCamaraConZoom(latLang: LatLng, zoom: Float = 15f) {
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLang, zoom)
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                permisos = true
                establecerConfiguracionMapa()
            } else {
                mostrarSnackbar("No se concedieron los permisos necesarios.")
            }
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_google_maps),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}