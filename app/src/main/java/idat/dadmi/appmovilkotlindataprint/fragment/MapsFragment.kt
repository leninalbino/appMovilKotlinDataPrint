package idat.dadmi.appmovilkotlindataprint.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import idat.dadmi.appmovilkotlindataprint.R

class MapsFragment : Fragment(), GoogleMap.OnMarkerDragListener {

    private lateinit var mMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->

        mMap = googleMap
        mMap.setOnMarkerDragListener(this)
        val libreria = LatLng(-12.07009, -76.94190)
        mMap.addMarker(MarkerOptions().position(libreria).title("Libreria Data Print").draggable(true)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gps)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(libreria, 16.0F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMarkerDragStart(p0: Marker) {
        var posicion = p0.position
        p0.snippet = posicion.latitude.toString()+ "-" +
                posicion.longitude.toString()
        p0.showInfoWindow()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(posicion))
    }

    override fun onMarkerDrag(p0: Marker) {
        p0.title = "Nueva ubicación de Referencia"
        p0.showInfoWindow()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(p0.position))
    }

    override fun onMarkerDragEnd(p0: Marker) {
        p0.showInfoWindow()
    }
}