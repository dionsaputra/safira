package com.ds.safira.screen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ds.safira.CustomGestureListener
import com.ds.safira.R
import com.ds.safira.adapter.ReviewAdapter
import com.ds.safira.data.AccidentPointRepo
import com.ds.safira.data.Review
import com.ds.safira.data.ReviewRepo
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.Image
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map
import com.here.android.mpa.mapping.MapMarker
import kotlinx.android.synthetic.main.fragment_maps.*


/**
 * A simple [Fragment] subclass.
 */
class MapsFragment : Fragment(), CustomGestureListener.MarkerSelectedListener,
    ReviewAdapter.Interaction {

    companion object {
        val STUB_POSITION = GeoCoordinate(-6.121260, 106.840458)
    }

    var map: Map? = null
    var markers = mutableMapOf<Int, MapMarker>()
    var reviewAdapter = ReviewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMaps()
        reviewRecycler.apply {
            adapter = reviewAdapter
        }
        fabSearch.setOnClickListener {
            reviewRecycler.visibility = View.GONE
        }
        mapsContainer.setOnClickListener {
            reviewRecycler.visibility = View.GONE
        }
//        maps.setOnTouchListener { _, _ ->
//            if (reviewRecycler.isVisible) {
//                reviewRecycler.visibility = View.GONE
//            }
//            true
//        }
    }

    private fun initMaps() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as AndroidXMapFragment
        mapFragment.init { error ->
            if (error === OnEngineInitListener.Error.NONE) {
                map = mapFragment.map
                map?.setCenter(STUB_POSITION, Map.Animation.LINEAR)
                fetchAccidentPoints()
                mapFragment.mapGesture.addOnGestureListener(CustomGestureListener(this))
            } else {
                println("ERROR: ${error.details}")
            }
        }
    }

    private fun fetchAccidentPoints() {
        for (accidentPoint in AccidentPointRepo.accidentPoints) {
            val coordinate = GeoCoordinate(accidentPoint.latitude, accidentPoint.longitude)
            val markerId = when {
                accidentPoint.accidentRate > 60 -> R.drawable.ic_marker_red
                accidentPoint.accidentRate > 30 -> R.drawable.ic_marker_yellow
                else -> R.drawable.ic_marker_green
            }
            val marker = MapMarker(coordinate, Image().apply { setImageResource(markerId) }).apply {
                title = accidentPoint.accidentRate.toString()
            }
            markers[accidentPoint.accidentPointId] = marker
            map?.addMapObject(marker)
        }
    }

    override fun onMarkerSelected(marker: MapMarker) {
        marker.showInfoBubble()
        reviewRecycler.visibility = View.VISIBLE
        reviewAdapter.swapData(ReviewRepo.reviews)
    }

    override fun onItemClick(item: Review) {

    }
}
