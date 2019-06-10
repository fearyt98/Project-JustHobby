package com.aurimteam.justhobby

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.PermissionChecker.checkSelfPermission

class GpsData() {
    /*private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    fun create(context: Context) {
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {

            }

            override fun onProviderDisabled(string: String?) {

            }

            override fun onProviderEnabled(string: String?) {

            }

            override fun onStatusChanged(string: String?, i: Int, bundle: Bundle?) {

            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (checkSelfPermission(
                    context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED &&
            ) {
                locationManager.requestLocationUpdates("gps", 5000, 0f, locationListener)
            }
    }*/
}