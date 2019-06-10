package com.aurimteam.justhobby.user.company_info.company_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.user.company_info.company_courses.CompanyCoursesFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.course.CourseAdapter
import com.aurimteam.justhobby.response.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_company_info.*
import kotlinx.android.synthetic.main.fragment_course_info.*

class CompanyInfoFragment : Fragment(), ICompanyInfoView {

    private val presenter = CompanyInfoPresenter(this, CompanyInfoModel())
    private val adapter = CourseAdapter(presenter)
    private var companyId: Long = 0
    private var company: CompanyResponseOneR? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_company_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            companyId = arguments!!.get("company_id")!!.toString().toLong()
            if (context != null) {
                presenter.getCompany(context!!, companyId)
                presenter.getCompanyCourses(context!!, companyId)
            }
        }
        companyInfoBtnBack.setOnClickListener { back() }
        companyInfoShowAllCourses.setOnClickListener { allCompanyCoursesFragment() }
        companyInfoCoursesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        companyInfoCoursesRecyclerView.adapter = adapter
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
            companyInfoProgressBar.visibility = View.VISIBLE
            companyInfoContent.visibility = View.GONE
        } else {
            companyInfoProgressBar.visibility = View.GONE
            companyInfoContent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun showCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        if (included != null) {
            toggleContentPB(false)
            adapter.onDataChange(courses, included)
        }
    }

    override fun deletedUserBookmark(position: Int) {
        adapter.deletedBookmark(position)
    }

    override fun addedUserBookmark(position: Int) {
        adapter.addedBookmark(position)
    }

    override fun bindCompanyInfo(company: CompanyResponseOneR) {
        this.company = company
        companyInfoTitle.text = company.attributes.title
        companyInfoCountCourses.text = company.relationships.count_courses.toString()
        companyInfoAddress.text = company.attributes.address
        companyInfoPhone.text = company.attributes.phone
        companyInfoCountReviews.text = company.relationships.count_reviews.toString()
        companyInfoDesc.text = company.attributes.description
        companyInfoSite.text = company.attributes.site
        Glide.with(this).load("").centerCrop().into(companyInfoImage)

        if (context != null) {
            val rating = company.attributes.rating
            if (rating != "-") {
                val ratingDouble = rating.toDouble()
                val color = when {
                    ratingDouble < 1.5 -> R.color.ratingOne
                    ratingDouble >= 1.5 && ratingDouble < 2.5 -> R.color.ratingTwo
                    ratingDouble >= 2.5 && ratingDouble < 3.5 -> R.color.ratingThree
                    ratingDouble >= 3.5 && ratingDouble < 4.5 -> R.color.ratingFour
                    ratingDouble >= 4.5 -> R.color.ratingFive
                    else -> R.color.grayDarkMiddle
                }
                companyInfoRating.setTextColor(ContextCompat.getColor(context!!, color))
                companyInfoRatingStar.setColorFilter(ContextCompat.getColor(context!!, color))
            } else {
                companyInfoRating.setTextColor(ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
                companyInfoRatingStar.setColorFilter(ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
            }
            companyInfoRating.text = rating
        }

        toggleContentPB(false)
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }

    private fun allCompanyCoursesFragment() {
        if (company != null) {
            val bundle = Bundle()
            bundle.putString("company_id", company!!.id.toString())
            bundle.putString("company_name", company!!.attributes.title)

            val companyCoursesFragment = CompanyCoursesFragment()
            companyCoursesFragment.arguments = bundle

            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, companyCoursesFragment)
                .commit()
        }
    }
}
