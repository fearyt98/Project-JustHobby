package com.aurimteam.justhobby.user.search.results

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse

class SearchResultAdapter : RecyclerView.Adapter<SearchResultHolder>() {

    private val foundCourses: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = foundCourses.size
    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(
            foundCourses[position].id,
            foundCourses[position].title,
            foundCourses[position].description,
            foundCourses[position].address,
            foundCourses[position].company,
            foundCourses[position].category
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchResultHolder =
        SearchResultHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
        )

    fun onDataChange(foundedCourses: List<CourseResponse>){
        foundCourses.clear()
        foundCourses.addAll(foundedCourses)
        notifyDataSetChanged()
    }
}