package com.aurimteam.justhobby.Main.HomeActivity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimeLineEventResponse
import kotlinx.android.synthetic.main.activity_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*



class HomeFragment : Fragment(), IHomeView {

    private val presenter = HomeMainTimeLinePresenter(this, HomeModel())
    private var adapter = HomeAdapter()

    override fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponse>) {
        adapter.onDataChange(eventsTimeLine)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.activity_home_timeline, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        eventsRecyclerView.adapter = adapter

        presenter.getEventsTimeLine()

        val dataFormatDay = SimpleDateFormat("d MMMM, EEEE")
        val currentDayString = dataFormatDay.format(Date())
        homeCalendarText.text = currentDayString

        val dataFormatTime = SimpleDateFormat("HH:mm")
        val currentTimeString = dataFormatTime.format(Date())
        homeCurrentTime.text = currentTimeString
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}