package com.aurimteam.justhobby.Main.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_main_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*

class HomeTimeLineClearFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_home_timeline_clear, container, false)
        //view.findViewById<ImageButton>(R.id.homeBookmarks).setOnClickListener { openUserBookmarks() }
        return view
    }
    override fun onResume() {
        super.onResume()
        homeCalendarText.text = SimpleDateFormat("d MMMM, EEEE").format(Date())
    }
}
