package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class AddressArrayAdapter(context: Context, resource: Int, private val items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {
}