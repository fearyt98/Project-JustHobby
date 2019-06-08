package com.aurimteam.justhobby.course

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import com.aurimteam.justhobby.response.CompanyResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.card_course.view.*

class CourseAdapter(private val presenter: ICoursePresenter) : RecyclerView.Adapter<CourseHolder>() {

    private val coursesList: MutableList<CourseResponseR> = mutableListOf()
    private val companyIncludedList: MutableMap<Long, CompanyResponse> = mutableMapOf()
    private val coursesOfUser: MutableList<Boolean> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseHolder =
        CourseHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
        )

    override fun onBindViewHolder(holder: CourseHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.changeColorBtnBookmark(coursesOfUser[position])
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
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
            if (coursesOfUser[position]) {
                deleteBookmark(
                    holder,
                    coursesList[position].id,
                    position
                )
            } else {
                addBookmark(
                    holder,
                    coursesList[position].id,
                    position
                )
            }
        }
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    fun onDataChange(courses: List<CourseResponseR>, included: IncludedResponse) {
        this.coursesList.clear()
        coursesList.addAll(courses)
        companyIncludedList.clear()
        if (included.companies != null)
            for (item in included.companies)
                companyIncludedList[item.id] = item
        for (item in coursesList)
            if (item.relationships.user != null)
                coursesOfUser.add(coursesList.indexOf(item), item.relationships.user)
            else
                coursesOfUser.add(coursesList.indexOf(item), false)
        notifyDataSetChanged()
    }

    fun deletedBookmark(position: Int) {
        coursesOfUser[position] = false
        notifyItemChanged(position, listOf(1))
    }

    fun addedBookmark(position: Int) {
        coursesOfUser[position] = true
        notifyItemChanged(position, listOf(1))
    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap", "granted")
    }

    private fun detailInfoCourse(fm: FragmentManager, course: CourseResponseR, company: CompanyResponse) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseInfoFragment())
            .commit()
    }

    private fun deleteBookmark(holder: CourseHolder, courseId: Long, position: Int) {
        presenter.deleteUserBookmark(holder.itemView.context, courseId, position)
    }

    private fun addBookmark(holder: CourseHolder, courseId: Long, position: Int) {
        presenter.addUserBookmark(holder.itemView.context, courseId, position)
    }
}