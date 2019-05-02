package com.aurimteam.justhobby.Main.HomeActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimelineResponses
import kotlinx.android.synthetic.main.activity_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*

class HomeTimeLineFragment : Fragment(), IHomeView {

    private val presenter = HomeMainTimeLinePresenter(this, HomeTimeLineModel())
    private var adapter = HomeTimeLineAdapter()

    override fun showTimeLineEvents(eventsTimeLine: List<TimelineResponses>) {
        adapter.onDataChange(eventsTimeLine)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home_timeline, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.getEventsTimeLine()
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        eventsRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        homeCalendarText.text = SimpleDateFormat("d MMMM, EEEE").format(Date())
        homeCurrentTime.text = SimpleDateFormat("HH:mm").format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}