package com.aurimteam.justhobby.Main.HomeActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimeLineEventResponse
import kotlinx.android.synthetic.main.activity_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity(), IHomeView {

    private val presenter = HomeMainTimeLinePresenter(this, HomeModel())
    private val adapter = HomeAdapter()

    override fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponse>) {
        adapter.onDataChange(eventsTimeLine)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_timeline)

        eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        eventsRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
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