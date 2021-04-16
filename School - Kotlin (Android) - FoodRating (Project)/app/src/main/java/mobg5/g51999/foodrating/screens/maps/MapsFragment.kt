package mobg5.g51999.foodrating.screens.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.screens.lobby.LobbyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MapsFragment : Fragment()
{
    //Retrieves the singleton instance of the LobbyViewModel.
    private val viewModel: LobbyViewModel by sharedViewModel(
        from = { parentFragment as ViewModelStoreOwner }
    )

    companion object
    {
        //Request permission code.
        private const val REQUEST_LOCATION_PERMISSION = 1
        private const val CITY_ZOOM_LEVEL = 11.0f
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    /**
     * Adjusts the maps UI and puts the markers.
     */
    private val callback = OnMapReadyCallback { googleMap ->
        val brussels = LatLng(50.85045, 4.34878)
        enableMyLocation(googleMap)
        addMarkers(googleMap)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brussels, CITY_ZOOM_LEVEL))
    }

    /**
     * Add the Durable Food Places markers.
     */
    private fun addMarkers(map: GoogleMap)
    {
        var coordinates: List<Double>?
        var title: String
        var markerPos: LatLng
        var snippet: String
        viewModel.foodData.value?.records?.forEach {
            coordinates = it.coordinates
            title = it.fieldsProperty.nom
            snippet = "Categorie: ${it.fieldsProperty.categorie} - Type: ${it.fieldsProperty.type}"
            if (coordinates != null)
            {
                markerPos = LatLng(coordinates!![1], coordinates!![0])
                map.addMarker(MarkerOptions().position(markerPos).title(title).snippet(snippet))
            }
        }
    }

    /**
     * Verifies for permissions and enables the GPS Location in the map.
     */
    @SuppressLint("MissingPermission")
    private fun enableMyLocation(map: GoogleMap)
    {
        if (isPermissionGranted())
        {
            map.isMyLocationEnabled = true
        } else
        {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION
            )
        }
    }

    /**
     * Verifies if the app has permissions to access GPS Position.
     */
    private fun isPermissionGranted(): Boolean
    {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

}