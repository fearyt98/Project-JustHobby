package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CompanyResponse
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import com.aurimteam.justhobby.user.main.home.user_bookmarks.UserBookmarksHolder
import kotlinx.android.synthetic.main.card_course.view.*

class PopularCoursesAdapter : RecyclerView.Adapter<PopularCoursesHolder>() {

    private val coursesList: MutableList<CourseResponseR> = mutableListOf()
    private val companyIncludedList: MutableMap<Long, CompanyResponse> = mutableMapOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PopularCoursesHolder =
        PopularCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
        )

    override fun onBindViewHolder(holder: PopularCoursesHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.changeColorBtnBookmark(payloads[0] as Boolean?)
    }
    override fun onBindViewHolder(holder: PopularCoursesHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val item = coursesList[position]
        val itemCompany = companyIncludedList[item.relationships.company.id]!!
        holder.bind(
            item.attributes.rating,
            item.attributes.title,
            itemCompany.attributes.title,
            item.attributes.address,
            item.attributes.length,
            item.attributes.status,
            item.attributes.type_payment,
            item.attributes.price,
            item.attributes.sex,
            item.attributes.age_max,
            item.attributes.age_min,
            item.relationships.user
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse(manager, item, itemCompany) }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener {
            /*deleteBookmark(
                holder,
                coursesList[position].id,
                position
            )
            addBookmark()*/
        }
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    fun onDataChange(courses: List<CourseResponseR>, included: IncludedResponse) {
        coursesList.clear()
        coursesList.addAll(courses)
        companyIncludedList.clear()
        if (included.companies != null)
            for (item in included.companies)
                companyIncludedList[item.id] = item
        notifyDataSetChanged()
    }

    private fun detailInfoCourse(fm: FragmentManager, course: CourseResponseR, company: CompanyResponse) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseInfoFragment())
            .commit()
    }

    private fun deleteBookmark(holder: UserBookmarksHolder, courseId: Long, position: Int) {
        //presenter.deleteUserBookmarks(holder.itemView.context, courseId, position)
    }

    private fun addBookmark() {

    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap", "granted")
    }
}