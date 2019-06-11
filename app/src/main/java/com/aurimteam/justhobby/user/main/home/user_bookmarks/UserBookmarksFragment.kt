package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import kotlinx.android.synthetic.main.fragment_user_bookmarks.*

class UserBookmarksFragment : Fragment(), IUserBookmarksView {

    private val presenter = UserBookmarksPresenter(this, UserBookmarksModel())
    private var adapter = UserBookmarksAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_bookmarks, container, false)
        view.findViewById<ImageButton>(R.id.bookmarksBtnBack).setOnClickListener { back() }
        return view
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null && activity != null) {
            val gpsData = (activity as MainNavActivity).gpsData
            presenter.getUserBookmarks(context!!, gpsData.returnLat(), gpsData.returnLon())
        }
        bookmarksRecyclerView.layoutManager = LinearLayoutManager(context)
        bookmarksRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            bookmarksProgressBar.visibility = View.VISIBLE
            bookmarksRecyclerView.visibility = View.GONE
        } else {
            bookmarksProgressBar.visibility = View.GONE
            bookmarksRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun showUserBookmarks(bookmarks: List<CourseResponseR>, included: IncludedResponse?) {
        if (bookmarks.isEmpty() || included == null) {
            bookmarksProgressBar.visibility = View.GONE
            bookmarksRecyclerView.visibility = View.GONE
            bookmarksClear.visibility = View.VISIBLE
        } else {
            toggleContentPB(false)
            adapter.onDataChange(bookmarks, included)
        }
    }

    override fun deletedUserBookmark(position: Int) {
        adapter.removeItem(position)
        if(adapter.itemCount == 0) {
            bookmarksProgressBar.visibility = View.GONE
            bookmarksRecyclerView.visibility = View.GONE
            bookmarksClear.visibility = View.VISIBLE
        }
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}
