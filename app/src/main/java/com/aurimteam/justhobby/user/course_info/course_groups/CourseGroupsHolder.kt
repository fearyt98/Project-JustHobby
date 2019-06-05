package com.aurimteam.justhobby.user.course_info.course_groups

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_group.view.*

class CourseGroupsHolder(view: View):RecyclerView.ViewHolder(view){
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