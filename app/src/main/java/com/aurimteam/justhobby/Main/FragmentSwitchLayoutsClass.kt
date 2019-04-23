package com.aurimteam.justhobby.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.aurimteam.justhobby.Main.HomeActivity.HomeAdapter
import kotlinx.android.synthetic.main.activity_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*


/*class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.activity_home_timeline, container, false)

        /*// Replace 'android.R.id.list' with the 'id' of your RecyclerView
        val mRecyclerView = view.findViewById(R.id.eventsRecyclerView) as RecyclerView
        val mLayoutManager = LinearLayoutManager(this.activity)
        mRecyclerView.layoutManager = mLayoutManager

        val mAdapter = HomeAdapter()
        mRecyclerView.adapter = mAdapter*/
        return view
    }

    override fun onStart() {
        super.onStart()

        val dataFormatDay = SimpleDateFormat("d MMMM, EEEE")
        val currentDayString = dataFormatDay.format(Date())
        homeCalendarText.text = currentDayString

        val dataFormatTime = SimpleDateFormat("HH:mm")
        val currentTimeString = dataFormatTime.format(Date())
        homeCurrentTime.text = currentTimeString
    }
}
*/
class RecommendedPopular : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home_timeline, null)
    }
}
