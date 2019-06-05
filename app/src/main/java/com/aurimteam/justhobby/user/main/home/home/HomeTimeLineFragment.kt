package com.aurimteam.justhobby.user.main.home.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.user.course_info.course_reviews.CourseReviewsFragment
import com.aurimteam.justhobby.user.main.home.user_courses.UserCoursesFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.EventResponse
import kotlinx.android.synthetic.main.fragment_main_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast


class HomeTimeLineFragment : Fragment(), IHomeView {

    private var presenter: HomeTimeLinePresenter? = null
    private var adapter = HomeTimeLineAdapter()
    private var dateAndTime = Calendar.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_home_timeline, container, false)
        view.findViewById<ImageButton>(R.id.homeBookmarks).setOnClickListener { openUserBookmarks() }
        view.findViewById<ImageButton>(R.id.homeCourses).setOnClickListener { openUserCourses() }
        view.findViewById<ImageButton>(R.id.homeCalendar).setOnClickListener { openDatePicker() }
        view.findViewById<TextView>(R.id.homeCalendarText).setOnClickListener { openDatePicker() }

        presenter = HomeTimeLinePresenter(
            this,
            HomeTimeLineModel(),
            container?.context
        )
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter?.getEventsTimeLine(SimpleDateFormat("dd.MM.yyyy").format(Date()))
        homeEventsRecyclerView.layoutManager = LinearLayoutManager(context)
        homeEventsRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        homeCalendarText.text = SimpleDateFormat("d MMMM, EEEE").format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    private var listenerDatePicker: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }

    private fun openDatePicker() {
        if (this.activity != null)
            DatePickerDialog(
                this.activity!!, R.style.DatePickerDialog, listenerDatePicker,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            ).show()
    }

    private fun setInitialDateTime() {
        homeCalendarText.text = SimpleDateFormat("d MMMM, EEEE").format(Date(dateAndTime.timeInMillis))

        val selectDate = GregorianCalendar()
        selectDate.time = Date(dateAndTime.timeInMillis)
        val currentDate = GregorianCalendar()
        val isNow = selectDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                selectDate.get(Calendar.DAY_OF_YEAR) == currentDate.get(Calendar.DAY_OF_YEAR)
        homeCurrentTime.visibility = if (isNow) View.VISIBLE else View.GONE

        presenter?.getEventsTimeLine(SimpleDateFormat("dd.MM.yyyy").format(Date(dateAndTime.timeInMillis)))
    }

    override fun showTimeLineEvents(eventsTimeLine: List<EventResponse>) {
        toggleContentPB()
        if (eventsTimeLine.isEmpty())
            showMessage("0")
        val selectDate = GregorianCalendar()
        selectDate.time = Date(dateAndTime.timeInMillis)
        val currentDate = GregorianCalendar()
        val isNow = selectDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                selectDate.get(Calendar.DAY_OF_YEAR) == currentDate.get(Calendar.DAY_OF_YEAR)
        adapter.onDataChange(eventsTimeLine, isNow)
    }

    override fun toggleContentPB() {
        if (homeContent.visibility != View.GONE) {
            homeProgressBar.visibility = View.VISIBLE
            homeContent.visibility = View.GONE
        } else {
            homeProgressBar.visibility = View.GONE
            homeContent.visibility = View.VISIBLE
        }
    }

    private fun openUserBookmarks() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseReviewsFragment())
            .commit()
    }

    private fun openUserCourses() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, UserCoursesFragment())
            .commit()
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(
            this.activity,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }
}