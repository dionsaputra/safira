package com.ds.safira

import android.graphics.PointF
import com.here.android.mpa.common.ViewObject
import com.here.android.mpa.mapping.MapGesture
import com.here.android.mpa.mapping.MapMarker
import com.here.android.mpa.mapping.MapObject

class CustomGestureListener(val markerSelectedListener: MarkerSelectedListener) :
    MapGesture.OnGestureListener {

    interface MarkerSelectedListener {
        fun onMarkerSelected(marker: MapMarker)
    }

    override fun onLongPressRelease() {

    }

    override fun onRotateEvent(p0: Float): Boolean {
        return false
    }

    override fun onMultiFingerManipulationStart() {

    }

    override fun onPinchLocked() {

    }

    override fun onPinchZoomEvent(p0: Float, p1: PointF?): Boolean {
        return false
    }

    override fun onTapEvent(p0: PointF?): Boolean {
        return false
    }

    override fun onPanStart() {}

    override fun onMultiFingerManipulationEnd() {}

    override fun onDoubleTapEvent(p0: PointF?): Boolean {
        return false
    }

    override fun onPanEnd() {}

    override fun onTiltEvent(p0: Float): Boolean {
        return false
    }

    override fun onMapObjectsSelected(p0: MutableList<ViewObject>?): Boolean {
        for (item in p0.orEmpty()) {
            if (item.baseType == ViewObject.Type.USER_OBJECT) {
                if ((item as MapObject).type == MapObject.Type.MARKER) {
                    markerSelectedListener.onMarkerSelected(item as MapMarker)
                }
            }
        }
        return false
    }

    override fun onRotateLocked() {
    }

    override fun onLongPressEvent(p0: PointF?): Boolean {
        return false
    }

    override fun onTwoFingerTapEvent(p0: PointF?): Boolean {
        return false
    }
}