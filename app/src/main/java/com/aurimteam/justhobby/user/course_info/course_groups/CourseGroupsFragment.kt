package com.aurimteam.justhobby.user.course_info.course_groups

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.group.GroupAdapter
import com.aurimteam.justhobby.response.GroupResponse
import kotlinx.android.synthetic.main.fragment_course_groups.*

class CourseGroupsFragment : Fragment(), ICourseGroupsView {

    private val presenter = CourseGroupsPresenter(this, CourseGroupsModel())
    private val adapter = GroupAdapter(presenter)
    private var courseId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_groups, container, false)
    }
    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            courseId = arguments!!.get("course_id")!!.toString().toLong()
            courseGroupsTitle.text = arguments!!.get("course_name")!!.toString()
            courseGroupsCompany.text = arguments!!.get("company_name")!!.toString()
            if (context != null)
                presenter.getCourseGroups(context!!, courseId)
        }
        courseGroupsBtnBack.setOnClickListener { back() }
        courseGroupsRecyclerView.layoutManager = LinearLayoutManager(context)
        courseGroupsRecyclerView.adapter = adapter
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
            courseGroupsProgressBar.visibility = View.VISIBLE
            courseGroupsRecyclerView.visibility = View.GONE
        } else {
            courseGroupsProgressBar.visibility = View.GONE
            courseGroupsRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun showCourseGroups(groups: List<GroupResponse>) {
        toggleContentPB(false)
        adapter.onDataChange(groups)
    }

    override fun deletedUserGroup(position: Int) {
        adapter.deletedGroup(position)
    }

    override fun addedUserGroup(position: Int) {
        adapter.addedGroup(position)
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}
