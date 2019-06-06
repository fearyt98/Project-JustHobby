package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class UserBookmarksAdapter(private val presenter: UserBookmarksPresenter) :
    RecyclerView.Adapter<CourseHolder>() {

    private val userBookmarksList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = userBookmarksList.size
    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.bind(
            userBookmarksList[position].type,
            userBookmarksList[position].id,
            userBookmarksList[position].attributes
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse() }
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

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseHolder = CourseHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
    )

    fun onDataChange(bookmarks: List<CourseResponse>) {
        userBookmarksList.clear()
        userBookmarksList.addAll(bookmarks)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse() {

    }

    private fun deleteBookmark(holder: CourseHolder, courseId: Long, position: Int) {
        presenter.deleteUserBookmarks(holder.itemView.context, courseId, position)
        userBookmarksList.removeAt(position)
    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap", "granted")
    }
}
