package com.aurimteam.justhobby.CompanyInfo.CompanyCoursesInfoActivity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_company_courses.*

class CompanyCoursesFragment : Fragment(), ICompanyCoursesView {

    private val presenter = CompanyCoursesPresenter(this, CompanyCoursesModel())
    private val adapter = CompanyCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_company_courses, container, false)
        view.findViewById<ImageButton>(R.id.backArrowCompanyCourses).setOnClickListener { backToCompanyInfoFragment() }
        return view
    }

    override fun showCompanyCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCompanyCourses()
        allCompanyCourses.layoutManager = LinearLayoutManager(context)
        allCompanyCourses.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToCompanyInfoFragment() {
        fragmentManager?.popBackStack()
    }
}
