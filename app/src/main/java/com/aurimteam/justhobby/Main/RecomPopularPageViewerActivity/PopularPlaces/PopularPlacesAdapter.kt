package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularPlaces

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class PopularPlacesAdapter : RecyclerView.Adapter<PopularPlacesHolder>() {
    private val placesList: MutableList<PopularPlacesHolder> = mutableListOf()

    override fun getItemCount(): Int = placesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PopularPlacesHolder =
        PopularPlacesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_card_popular_place,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PopularPlacesHolder, position: Int) {
        //holder.bind()
    }
    /*fun onDataChange(places: List<PopularPlacesResponse>) {
        this.placesList.clear()
        this.placesList.addAll(places)
        notifyDataSetChanged()
    }*/
}