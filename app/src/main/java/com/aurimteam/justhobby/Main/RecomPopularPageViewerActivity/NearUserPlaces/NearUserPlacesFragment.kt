package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.NearUserPlaces

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.INearPlacesView
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_near_user_places_fragment.*

class NearUserPlacesFragment : Fragment(), INearPlacesView {

    //private val presenter = HomeMainTimeLinePresenter(this, HomeTimeLineModel())
    //private var adapter = HomeTimeLineAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_near_user_places_fragment, container, false)
    }

    override fun showNearUserPlaces() {

    }

    override fun onStart() {
        super.onStart()
        //presenter.getEventsTimeLine()
        nearPlacesRecyclerView.layoutManager = LinearLayoutManager(context)
        //PopularCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.onDestroy()
    }
}
