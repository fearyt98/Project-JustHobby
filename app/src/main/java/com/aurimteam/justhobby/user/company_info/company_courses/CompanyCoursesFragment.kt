package com.aurimteam.justhobby.user.company_info.company_courses


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import kotlinx.android.synthetic.main.fragment_company_courses.*

class CompanyCoursesFragment : Fragment(), ICompanyCoursesView {

    private val presenter = CompanyCoursesPresenter(this, CompanyCoursesModel())
    private val adapter = CompanyCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_company_courses, container, false)
        view.findViewById<ImageButton>(R.id.companyCoursesBtnBack).setOnClickListener { backToCompanyInfoFragment() }
        return view
    }

    override fun showCompanyCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCompanyCourses()
        companyCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        companyCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToCompanyInfoFragment() {
        fragmentManager?.popBackStack()
    }
}