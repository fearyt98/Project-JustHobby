package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_group.view.*

class CourseGroupsHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(
        id: Long,
        title: String,
        tutor: String,
        price: String
    ) {
        itemView.cardGroupPrice.text = price
        itemView.tutorGroup.text = tutor
        itemView.titleGroup.text = title
    }
}