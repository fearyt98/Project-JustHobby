package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class PopularCoursesAdapter : RecyclerView.Adapter<PopularCoursesHolder>() {
    private val placesList: MutableList<PopularCoursesHolder> = mutableListOf()

    override fun getItemCount(): Int = placesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PopularCoursesHolder =
        PopularCoursesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_card_course,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PopularCoursesHolder, position: Int) {
        //holder.bind()
    }
    /*fun onDataChange(places: List<PopularCoursesResponse>) {
        this.placesList.clear()
        this.placesList.addAll(places)
        notifyDataSetChanged()
    }*/
}