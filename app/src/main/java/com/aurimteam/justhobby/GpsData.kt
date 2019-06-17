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
    var isCreated = false
    private var isDeactivated = false
    fun create(activity: Activity, context: Context) {
        isCreated = true
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                if(!isDeactivated) {
                    lat = location?.latitude
                    lon = location?.longitude
                } else {
                    lat = null
                    lon = null
                }
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
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                requestPermissions(activity, permissions, 10)
                return
            } else {
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
            }
    }

    fun configureGpsData() {
        if (isCreated) {
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
        }
    }

    fun activate() {
        if(isCreated) {
            isDeactivated = false
            lat = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).latitude
            lon = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).longitude
        }
    }

    fun deactivate() {
        isDeactivated = true
        lat = null
        lon = null
    }

    fun returnLat(): Float? {
        return if (lat == null) null else lat!!.toFloat()
    }

    fun returnLon(): Float? {
        return if (lon == null) null else lon!!.toFloat()
    }
}
