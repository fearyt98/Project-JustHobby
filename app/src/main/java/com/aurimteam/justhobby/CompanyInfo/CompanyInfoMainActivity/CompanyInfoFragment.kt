package com.aurimteam.justhobby.CompanyInfo.CompanyInfoMainActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.aurimteam.justhobby.CompanyInfo.CompanyCoursesInfoActivity.CompanyCoursesFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_company_info.*

class CompanyInfoFragment : Fragment(), ICompanyInfoView {

    private val presenter = CompanyInfoPresenter(this, CompanyInfoModel())
    private val adapter = CompanyInfoAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_company_info, container, false)
        view.findViewById<ImageButton>(R.id.companyInfoBtnBack).setOnClickListener { backToRecommendedFragment() }
        view.findViewById<TextView>(R.id.companyInfoShowAllCourses).setOnClickListener { allCompanyCoursesFragment() }
        return view
    }

    override fun showCompanyCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCompanyCourses()
        companyInfoCoursesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        companyInfoCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToRecommendedFragment() {
        fragmentManager?.popBackStack()
    }

    private fun allCompanyCoursesFragment() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CompanyCoursesFragment())
            .commit()
    }
}
