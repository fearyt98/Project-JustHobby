package com.aurimteam.justhobby.user.course_info.сourse_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.user.course_info.course_groups.CourseGroupsFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.group.GroupAdapter
import com.aurimteam.justhobby.response.CourseResponseOneR
import com.aurimteam.justhobby.response.GroupResponse
import com.aurimteam.justhobby.user.company_info.company_info.CompanyInfoFragment
import com.aurimteam.justhobby.user.course_info.course_reviews.CourseReviewsFragment
import kotlinx.android.synthetic.main.fragment_course_info.*

class CourseInfoFragment : Fragment(), ICourseInfoView {
    private val presenter = CourseInfoPresenter(this, CourseInfoModel())
    private val adapter = GroupAdapter(presenter)
    private var isBookmark = false
    private var courseId: Long = 0
    private var course: CourseResponseOneR? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            courseId = arguments!!.get("course_id")!!.toString().toLong()
            if (context != null) {
                presenter.getCourse(context!!, courseId)
                presenter.getCourseGroups(context!!, courseId)
            }
        }
        courseInfoBtnBack.setOnClickListener { back() }
        courseInfoBtnBookmark.setOnClickListener {
            if (isBookmark) {
                presenter.deleteUserBookmark(context!!, courseId)
            } else {
                presenter.addUserBookmark(context!!, courseId)
            }
        }
        courseInfoCompany.setOnClickListener { openCompany() }
        courseInfoShowAllCourses.setOnClickListener { openAllGroups() }
        courseInfoReviews.setOnClickListener { openReviews() }
        courseInfoGroupsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        courseInfoGroupsRecyclerView.adapter = adapter
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
            courseInfoProgressBar.visibility = View.VISIBLE
            courseInfoContent.visibility = View.GONE
        } else {
            courseInfoProgressBar.visibility = View.GONE
            courseInfoContent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun showCourseGroups(groups: List<GroupResponse>) {
        adapter.onDataChange(groups)
    }

    override fun deletedUserGroup(position: Int) {
        adapter.deletedGroup(position)
    }

    override fun addedUserGroup(position: Int) {
        adapter.addedGroup(position)
    }

    override fun bindCourseInfo(course: CourseResponseOneR) {
        this.course = course
        val company = course.relationships.company
        courseInfoTitle.text = course.attributes.title
        courseInfoCompany.text = company.attributes.title
        courseInfoAddress.text = course.attributes.address
        courseInfoPhone.text = company.attributes.phone
        courseInfoCountReviews.text = course.relationships.count_reviews.toString()
        courseInfoCountGroups.text = course.relationships.count_groups.toString()
        courseInfoDesc.text = course.attributes.description

        changeColorBtnBookmark(course.relationships.user)

        if (context != null) {
            courseInfoPrice.text =
                String.format(context!!.resources.getString(R.string.payment_one), course.attributes.price)
            val rating = course.attributes.rating
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
                courseInfoRating.setTextColor(ContextCompat.getColor(context!!, color))
                courseInfoRatingStar.setColorFilter(ContextCompat.getColor(context!!, color))
            } else {
                courseInfoRating.setTextColor(ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
                courseInfoRatingStar.setColorFilter(ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
            }
            courseInfoRating.text = rating
        }

        toggleContentPB(false)
    }

    override fun changeColorBtnBookmark(user: Boolean?) {
        if (context != null)
            if (user != null && user) {
                isBookmark = true
                courseInfoBtnBookmark.setColorFilter(
                    ContextCompat.getColor(context!!, R.color.colorPrimary)
                )
            } else {
                isBookmark = false
                courseInfoBtnBookmark.setColorFilter(
                    ContextCompat.getColor(context!!, R.color.gray)
                )
            }
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }

    private fun openCompany() {
        if(course != null) {
            val bundle = Bundle()
            bundle.putString("company_id", course!!.relationships.company.id.toString())

            val companyInfoFragment = CompanyInfoFragment()
            companyInfoFragment.arguments = bundle

            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, companyInfoFragment)
                .commit()
        }
    }

    private fun openAllGroups() {
        if(course != null) {
            val bundle = Bundle()
            bundle.putString("course_id", course!!.id.toString())
            bundle.putString("course_name", course!!.attributes.title)
            bundle.putString("company_name", course!!.relationships.company.attributes.title)

            val courseGroupsFragment = CourseGroupsFragment()
            courseGroupsFragment.arguments = bundle

            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, courseGroupsFragment)
                .commit()
        }
    }

    private fun openReviews() {
        if(course != null) {
            val bundle = Bundle()
            bundle.putString("course_id", course!!.id.toString())
            bundle.putString("course_name", course!!.attributes.title)
            bundle.putString("company_name", course!!.relationships.company.attributes.title)
            bundle.putString("rating", course!!.attributes.rating)
            bundle.putString("count_reviews", course!!.relationships.count_reviews.toString())

            val courseReviewsFragment = CourseReviewsFragment()
            courseReviewsFragment.arguments = bundle

            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, courseReviewsFragment)
                .commit()
        }
    }

}
