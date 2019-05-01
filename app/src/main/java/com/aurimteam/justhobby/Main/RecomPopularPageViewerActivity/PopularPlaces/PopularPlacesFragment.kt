package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularPlaces

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularPlacesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.PopularPlacesResponse
import kotlinx.android.synthetic.main.activity_popular_places_fragment.*

class PopularPlacesFragment : Fragment(), IPopularPlacesView {

    private val presenter = PopularPlacesPresenter(this, PopularPlacesModel())
    private var adapter = PopularPlacesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_popular_places_fragment, container, false)
    }

    override fun showPopularPlaces(places: List<PopularPlacesResponse>) {
        //adapter.onDataChange()
    }

    override fun onStart() {
        super.onStart()
        presenter.getPopularPlaces()
        popularPlacesRecyclerView.layoutManager = LinearLayoutManager(context)
        popularPlacesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
