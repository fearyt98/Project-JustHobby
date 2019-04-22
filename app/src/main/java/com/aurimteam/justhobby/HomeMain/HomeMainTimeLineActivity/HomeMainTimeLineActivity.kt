package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimeLineEventResponse
import kotlinx.android.synthetic.main.activity_home_main_general.*
import java.text.SimpleDateFormat
import java.util.*

class HomeMainTimeLineActivity : AppCompatActivity(), IHomeMainView {

    private val presenter = HomeMainTimeLinePresenter(this, HomeMainTimeLineModel())
    private val adapter = HomeMainTimeLineAdapter()

    override fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponse>) {
        adapter.onDataChange(eventsTimeLine)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main_general)

        eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        eventsRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.getEventsTimeLine()

        val df = SimpleDateFormat("d MMMM, EEEE")
        val currentDateTimeString = df.format(Date())
        homeCalendarText.text = currentDateTimeString
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}