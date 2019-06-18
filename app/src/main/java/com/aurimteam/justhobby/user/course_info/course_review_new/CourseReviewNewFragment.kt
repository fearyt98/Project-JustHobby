package com.aurimteam.justhobby.user.course_info.course_review_new

import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_course_review_new.*
import android.os.Build
import android.support.annotation.ColorInt
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.Settings



class CourseReviewNewFragment : Fragment(), ICourseReviewNewView {

    private val presenter = CourseReviewNewPresenter(this, CourseReviewNewModel())
    private var courseId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_review_new, container, false)
        view.findViewById<ImageButton>(R.id.reviewNewBtnBack).setOnClickListener { back() }
        view.findViewById<ImageButton>(R.id.reviewNewBtnSend).setOnClickListener { send() }
        return view
    }

    override fun onStart() {
        super.onStart()

        if(!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            courseId = arguments!!.get("courseId")!!.toString().toLong()
        }

        setRatingStarColor(
            (reviewNewRatingBar.progressDrawable as LayerDrawable).getDrawable(2),
            ContextCompat.getColor(context!!, R.color.ratingFive)
        )

        setRatingStarColor(
            (reviewNewRatingBar.progressDrawable as LayerDrawable).getDrawable(1),
            ContextCompat.getColor(context!!, android.R.color.transparent)
        )
        setRatingStarColor(
            (reviewNewRatingBar.progressDrawable as LayerDrawable).getDrawable(0),
            ContextCompat.getColor(context!!, R.color.gray)
        )

        reviewNewRatingBar.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            if (context != null) {
                ratingBar.rating = rating
                val stars = ratingBar.progressDrawable as LayerDrawable

                when (rating) {
                    1f -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.ratingOne)
                    )
                    2f -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.ratingTwo)
                    )
                    3f -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.ratingThree)
                    )
                    4f -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.ratingFour)
                    )
                    5f -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.ratingFive)
                    )
                    else -> setRatingStarColor(
                        stars.getDrawable(2),
                        ContextCompat.getColor(context!!, R.color.grayDarkMiddle)
                    )
                }

                setRatingStarColor(
                    stars.getDrawable(1),
                    ContextCompat.getColor(context!!, android.R.color.transparent)
                )
                setRatingStarColor(
                    stars.getDrawable(0),
                    ContextCompat.getColor(context!!, R.color.gray)
                )
            }
        }

        reviewNewEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                reviewNewLimit.visibility = if (s.length >= 20) View.GONE else View.VISIBLE
            }
        })
    }

    private fun setRatingStarColor(drawable: Drawable, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun hideErrors() {
    }

    override fun showError(strError: String) {
        reviewNewEditText.setError(strError, null)
    }

    override fun showMessage(message: String?, isImportant: Boolean) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if ((devMode != null && devMode) || isImportant) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
    }

    override fun back() {
        fragmentManager?.popBackStack()
    }

    private fun send() {
        if (context != null)
            presenter.sendNewReview(
                courseId,
                reviewNewEditText.text.toString(),
                reviewNewRatingBar.rating.toInt(),
                context!!
            )

    }
}
