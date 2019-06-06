package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class UserBookmarksAdapter(private val presenter: UserBookmarksPresenter) :
    RecyclerView.Adapter<UserBookmarksHolder>() {

    private val userBookmarksList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = userBookmarksList.size
    override fun onBindViewHolder(holder: UserBookmarksHolder, position: Int) {
        holder.bind(
            userBookmarksList[position].id,
            userBookmarksList[position].title,
            userBookmarksList[position].description,
            userBookmarksList[position].address,
            userBookmarksList[position].company,
            userBookmarksList[position].category
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse() }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener {
            deleteBookmark(
                holder,
                userBookmarksList[position].id,
                position
            )
        }
        holder.itemView.cardCourseBtnBookmark.setColorFilter(R.color.red)
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserBookmarksHolder = UserBookmarksHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
    )

    fun onDataChange(bookmarks: List<CourseResponse>) {
        userBookmarksList.clear()
        userBookmarksList.addAll(bookmarks)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse() {

    }

    private fun deleteBookmark(holder: UserBookmarksHolder, courseId: Long, position: Int) {
        presenter.deleteUserBookmarks(holder.itemView.context, courseId, position)
        userBookmarksList.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap", "granted")
    }
}
