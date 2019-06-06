package com.aurimteam.justhobby.user.course_info.сourse_info

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_group.view.*

class CourseInfoGroupsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        teacher: String,
        price: Int
    ) {
        itemView.cardGroupPrice.text = price.toString()
        itemView.cardGroupTeacher.text = teacher
        itemView.cardGroupTitle.text = title
    }
}