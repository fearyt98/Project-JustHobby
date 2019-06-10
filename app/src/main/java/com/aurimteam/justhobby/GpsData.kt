package com.aurimteam.justhobby

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.PermissionChecker.checkSelfPermission

class GpsData {
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var lat: Double? = null
    private var lon: Double? = null
    fun create(activity: Activity, context: Context) {
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                lat = location?.latitude
                lon = location?.longitude
            }

            override fun onProviderDisabled(string: String?) {}
            override fun onProviderEnabled(string: String?) {}
            override fun onStatusChanged(string: String?, i: Int, bundle: Bundle?) {}
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(activity, permissions, 10)
                return
            } else {
                locationManager!!.requestLocationUpdates("gps", 5000, 0f, locationListener)
            }
    }

    fun configureGpsData() {
        locationManager!!.requestLocationUpdates("gps", 5000, 0f, locationListener)
    }

    fun returnLat(): Double {
        return lat!!
    }

    fun returnLon(): Double {
        return lon!!
    }
}

/*
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                configureGpsData()
                else Toast.makeText(context, "Разрешения не выданы", Toast.LENGTH_SHORT).show()
            }
        }
    }
 */
