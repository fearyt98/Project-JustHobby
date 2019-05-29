package com.aurimteam.justhobby.Main.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.Main.Home.UserCourses.UserCoursesFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimelineResponses
import com.aurimteam.justhobby.SearchActivity.SearchFragment
import kotlinx.android.synthetic.main.fragment_main_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*

class HomeTimeLineFragment : Fragment(), IHomeView {

    private val presenter = HomeTimeLinePresenter(this, HomeTimeLineModel())
    private var adapter = HomeTimeLineAdapter()

    override fun showTimeLineEvents(eventsTimeLine: List<TimelineResponses>) {
        adapter.onDataChange(eventsTimeLine)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_home_timeline, container, false)
        view.findViewById<ImageButton>(R.id.homeBookmarks).setOnClickListener { openUserBookmarks() }
        view.findViewById<ImageButton>(R.id.homeCourses).setOnClickListener { openUserCourses() }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getEventsTimeLine()
        homeEventsRecyclerView.layoutManager = LinearLayoutManager(context)
        homeEventsRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        homeCalendarText.text = SimpleDateFormat("d MMMM, EEEE").format(Date())
        //homeCurrentTime.text = SimpleDateFormat("HH:mm").format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun openUserBookmarks() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, SearchFragment())
            .commit()
    }

    private fun openUserCourses() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, UserCoursesFragment())
            .commit()
    }
}