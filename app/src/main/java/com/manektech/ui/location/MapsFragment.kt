package com.manektech.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.manektech.R
import com.manektech.data.entities.RestaurantItem

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val restaurantItemlat =arguments?.getString("lat")
        val restaurantItemlong =arguments?.getString("longg")
        val restaurantItemtitle =arguments?.getString("title")
        val title = restaurantItemlong?.toDouble()?.let {
            restaurantItemlat?.toDouble()?.let { it1 ->
                LatLng(
                    it1,
                    it
                )
            }
        }
        googleMap.addMarker(MarkerOptions().position(title).title(restaurantItemtitle))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(title))
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}