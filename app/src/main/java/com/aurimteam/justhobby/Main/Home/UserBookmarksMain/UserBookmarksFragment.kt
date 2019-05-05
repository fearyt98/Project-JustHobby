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
import kotlinx.android.synthetic.main.activity_user_bookmarks_fragment.*

class UserBookmarksFragment : Fragment(), IUserBookmarksView {


    private val presenter = UserBookmarksPresenter(this, UserBookmarksModel())
    private var adapter = UserBookmarksAdapter()

    override fun showUserBookmarks(bookmarks: List<CourseResponse>) {
        adapter.onDataChange(bookmarks)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_user_bookmarks_fragment, container, false)
        view.findViewById<ImageButton>(R.id.btnBackTimeLineFragment).setOnClickListener { backToTimeLineFragment() }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getUserBookmarks()
        userBookmarksRecyclerView.layoutManager = LinearLayoutManager(context)
        userBookmarksRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToTimeLineFragment() {
        fragmentManager?.popBackStack()
    }
}
