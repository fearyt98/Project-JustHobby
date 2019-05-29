package com.aurimteam.justhobby.Main.Home.UserBookmarksMain

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_user_bookmarks.*

class UserBookmarksFragment : Fragment(), IUserBookmarksView {


    private val presenter = UserBookmarksPresenter(this, UserBookmarksModel())
    private var adapter = UserBookmarksAdapter()

    override fun showUserBookmarks(bookmarks: List<CourseResponse>) {
        adapter.onDataChange(bookmarks)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_bookmarks, container, false)
        view.findViewById<ImageButton>(R.id.bookmarksBtnBack).setOnClickListener { backToTimeLineFragment() }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getUserBookmarks()
        bookmarksRecyclerView.layoutManager = LinearLayoutManager(context)
        bookmarksRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToTimeLineFragment() {
        fragmentManager?.popBackStack()
    }
}
