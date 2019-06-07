package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CompanyResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import kotlinx.android.synthetic.main.card_course.view.*

class UserBookmarksAdapter(private val presenter: UserBookmarksPresenter) :
    RecyclerView.Adapter<UserBookmarksHolder>() {

    private val userBookmarksList: MutableList<CourseResponseR> = mutableListOf()
    private val companyIncludedList: MutableMap<Long, CompanyResponse> = mutableMapOf()

    override fun getItemCount(): Int = userBookmarksList.size
    override fun onBindViewHolder(holder: UserBookmarksHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val item = userBookmarksList[position]
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
            item.attributes.age_min
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse(manager, item, itemCompany) }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener {
            deleteBookmark(
                holder,
                userBookmarksList[position].id,
                position
            )
        }
        holder.itemView.cardCourseBtnBookmark.setColorFilter(
            ContextCompat.getColor(holder.itemView.context, R.color.red)
        )
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserBookmarksHolder = UserBookmarksHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
    )

    fun onDataChange(bookmarks: List<CourseResponseR>, included: IncludedResponse) {
        userBookmarksList.clear()
        userBookmarksList.addAll(bookmarks)
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
        presenter.deleteUserBookmarks(holder.itemView.context, courseId, position)
    }

    fun removeItem(position: Int) {
        userBookmarksList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userBookmarksList.count())
    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap", "granted")
    }
}
