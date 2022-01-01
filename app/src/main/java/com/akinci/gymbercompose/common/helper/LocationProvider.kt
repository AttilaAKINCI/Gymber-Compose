package com.akinci.gymbercompose.common.helper

import android.location.Location

object LocationProvider {

    /** Calculate air distance between 2 locations with some Math :)
     *  For initial I give Istanbul's coordinates
     * **/
    fun calculateDistanceByKm(
       lat1: Double = 41.0082, lon1: Double = 28.9784, lat2: Double, lon2: Double
    ): Int {
        val startLocation = Location("start").apply {
            latitude = lat1
            longitude = lon1
        }

        val destinationLocation = Location("destination").apply {
            latitude = lat2
            longitude = lon2
        }

        return (startLocation.distanceTo(destinationLocation) / 1000).toInt()
    }
}