package com.aurimteam.justhobby.user.course_info.course_reviews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.ReviewResponse
import com.aurimteam.justhobby.user.course_info.course_review.CourseReviewFragment
import com.aurimteam.justhobby.user.course_info.course_review_new.CourseReviewNewFragment
import kotlinx.android.synthetic.main.fragment_course_reviews.*

class CourseReviewsFragment : Fragment(), ICourseReviewsView {

    private val presenter = CourseReviewsPresenter(this, CourseReviewsModel())
    private val adapter = CourseReviewsAdapter()
    private var courseId: Long = 0
    private var courseName: String = ""
    private var companyName: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_reviews, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            courseId = arguments!!.get("course_id")!!.toString().toLong()
            if (context != null) {
                presenter.getCourseReviews(context!!, courseId)
            }
            courseName = arguments!!.get("course_name")!!.toString()
            companyName = arguments!!.get("company_name")!!.toString()
            courseReviewsTitle.text = courseName
            courseReviewsCompany.text = companyName
            val rating = arguments!!.get("rating")!!.toString()
            courseReviewsRating.text = arguments!!.get("rating")!!.toString()
            if (context != null) {
                val countReviews = arguments!!.get("count_reviews")!!.toString().toInt()
                val stringId = termNum(
                    countReviews,
                    R.string.count_reviews_term0andTerm5toMoreAnd11to14,
                    R.string.count_reviews_term0andTerm5toMoreAnd11to14,
                    R.string.count_reviews_term1,
                    R.string.count_reviews_term2to4,
                    R.string.count_reviews_term0andTerm5toMoreAnd11to14
                )
                courseReviewsCountReviews.text = String.format(context!!.resources.getString(stringId), countReviews)
                val ratingDouble = rating.toDouble()
                courseReviewsRatingBar.rating = (Math.round(ratingDouble * 10.0) / 10.0).toFloat()
                val stars = courseReviewsRatingBar.progressDrawable
                when {
                    ratingDouble < 1.5 -> DrawableCompat.setTint(
                        stars,
                        ContextCompat.getColor(context!!, R.color.ratingOne)
                    )
                    ratingDouble >= 1.5 && ratingDouble < 2.5 -> DrawableCompat.setTint(
                        stars,
                        ContextCompat.getColor(context!!, R.color.ratingTwo)
                    )
                    ratingDouble >= 2.5 && ratingDouble < 3.5 -> DrawableCompat.setTint(
                        stars,
                        ContextCompat.getColor(context!!, R.color.ratingThree)
                    )
                    ratingDouble >= 3.5 && ratingDouble < 4.5 -> DrawableCompat.setTint(
                        stars,
                        ContextCompat.getColor(context!!, R.color.ratingFour)
                    )
                    ratingDouble >= 4.5 -> DrawableCompat.setTint(
                        stars,
                        ContextCompat.getColor(context!!, R.color.ratingFive)
                    )
                    else -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
                }
            }
        }
        courseReviewsBtnBack.setOnClickListener { back() }
        courseReviewsClearBtn.setOnClickListener { openReviewNew() }
        courseReviewsNewBtn.setOnClickListener { openReviewNew() }
        courseReviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        courseReviewsRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(courseReviewsRecyclerView, false)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun hideBtnForReview(hide: Boolean) {
        if (hide) {
            courseReviewsNewBtn.visibility = View.GONE
            courseReviewsLine.visibility = View.GONE
            courseReviewsMarginLine.visibility = View.VISIBLE
        } else {
            courseReviewsNewBtn.visibility = View.VISIBLE
            courseReviewsLine.visibility = View.VISIBLE
            courseReviewsMarginLine.visibility = View.GONE
        }
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            courseReviewsProgressBar.visibility = View.VISIBLE
            courseReviewsContent.visibility = View.GONE
        } else {
            courseReviewsProgressBar.visibility = View.GONE
            courseReviewsContent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun showCourseReviews(courseReviews: List<ReviewResponse>) {
        if (courseReviews.isEmpty()) {
            courseReviewsProgressBar.visibility = View.GONE
            courseReviewsContent.visibility = View.GONE
            courseReviewsContent.visibility = View.VISIBLE
        } else {
            toggleContentPB(false)
            adapter.onDataChange(courseReviews, courseName, companyName)
        }
    }

    private fun termNum(
        num: Int,
        term11to14: Int,
        term0: Int,
        term1: Int,
        term2to4: Int,
        term5toMore: Int
    ): Int {
        var term = term11to14
        if (num <= 10 || num >= 15) {
            val number = num.toString().substring(num.toString().length - 1).toInt()
            if (number == 0) {
                term = term0; }
            if (number == 1) {
                term = term1; }
            if (number > 1) {
                term = term2to4; }
            if (number > 4) {
                term = term5toMore; }
        }
        return term
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }

    private fun openReviewNew() {
        if (courseId > 0) {
            val bundle = Bundle()
            bundle.putString("courseId", courseId.toString())

            val courseReviewNewFragment = CourseReviewNewFragment()
            courseReviewNewFragment.arguments = bundle

            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, courseReviewNewFragment)
                .commit()
        }
    }
}