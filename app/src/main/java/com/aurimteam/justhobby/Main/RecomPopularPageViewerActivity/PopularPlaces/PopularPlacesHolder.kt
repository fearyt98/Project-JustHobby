package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.Response.IdentifierResponse
import java.sql.Timestamp

class PopularCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String,
        company: IdentifierResponse,
        category: IdentifierResponse
    ) {


    }
}