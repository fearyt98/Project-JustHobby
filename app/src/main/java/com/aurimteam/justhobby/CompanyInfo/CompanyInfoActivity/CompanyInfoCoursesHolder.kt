package com.aurimteam.justhobby.CompanyInfo.CompanyInfoActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.Response.IdentifierResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class CompanyInfoCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var presenter: CompanyInfoPresenter? = null
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String,
        company: IdentifierResponse,
        category: IdentifierResponse
    ) {
        itemView.cardCourseTitle.text = title
        itemView.cardCourseDescription.text = description
        itemView.cardCourseAddress.text = address
        itemView.cardCourseMetres.text = "750 м"
    }
}