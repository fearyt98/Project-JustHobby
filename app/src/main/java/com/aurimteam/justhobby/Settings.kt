package com.aurimteam.justhobby

import android.content.Context
import android.content.SharedPreferences

class Settings(val context: Context) {
    private val FILENAME = "com.aurimteam.justhobby.prefs"
    private var settings: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        settings = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        editor = settings?.edit()
        editor?.apply()
    }

    fun setProperty(name: String, value: String) {
        editor?.putString(name, value)
        editor?.apply()
    }

    fun getProperty(name: String): String? {
        return settings?.getString(name, null)
    }

    fun removeProperty(name: String) {
        editor?.remove(name)
        editor?.apply()
    }
}