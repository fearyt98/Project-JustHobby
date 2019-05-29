package com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_group.view.*

class CourseInfoGroupsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        teacher: String,
        price: String
    ) {
        itemView.cardGroupPrice.text = price
        itemView.cardGroupTeacher.text = teacher
        itemView.cardGroupTitle.text = title
    }
}