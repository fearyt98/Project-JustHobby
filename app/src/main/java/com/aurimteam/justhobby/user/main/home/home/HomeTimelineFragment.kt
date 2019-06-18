package com.aurimteam.justhobby.user.main.home.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.user.main.home.user_groups.UserGroupsFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.EventResponse
import kotlinx.android.synthetic.main.fragment_main_home_timeline.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.view.Gravity
import android.widget.Toast
import com.aurimteam.justhobby.response.TimelineNearDayResponse
import com.aurimteam.justhobby.user.main.home.user_bookmarks.UserBookmarksFragment
import android.os.CountDownTimer
import android.support.design.widget.BottomNavigationView
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.user.search.search.SearchFragment

class HomeTimelineFragment : Fragment(), IHomeView {

    private var presenter = HomeTimelinePresenter(this, HomeTimelineModel())
    private var adapter = HomeTimelineAdapter()
    private var isTimeline = true
    //private var oldDate = Calendar.getInstance()
    private var selectDate = Calendar.getInstance()
    private var currentDate = Calendar.getInstance()

    private var listenerDatePicker: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            //oldDate.timeInMillis = selectDate.timeInMillis
            selectDate.set(Calendar.YEAR, year)
            selectDate.set(Calendar.MONTH, monthOfYear)
            selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }

    private val timer = object : CountDownTimer(5000, 2000) {
        override fun onTick(millisUntilFinished: Long) {
            if (!isOneDay(currentDate, Calendar.getInstance())) {
                if (isOneDay(currentDate, selectDate)) {
                    selectDate = Calendar.getInstance()
                    setInitialDateTime()
                }
                currentDate = Calendar.getInstance()
            }
            val layoutManager = homeEventsRecyclerView.layoutManager as LinearLayoutManager
            val first = layoutManager.findFirstVisibleItemPosition()
            val last = layoutManager.findLastVisibleItemPosition()

            for (i in first..last) adapter.notifyItemChanged(i, listOf(1))
            adapter.removeIfIs()
            if (adapter.itemCount == 0 && context != null) {
                homeProgressBar.visibility = View.GONE
                homeContent.visibility = View.GONE
                homeContentDayClear.visibility = View.VISIBLE
            }
                //presenter.getNearDayTimeline(true, context!!)
        }

        override fun onFinish() {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_home_timeline, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (context != null)
            presenter.getNearDayTimeline(false, context!!)

        homeBookmarks.setOnClickListener { openUserBookmarks() }
        homeCourses.setOnClickListener { openUserCourses() }
        homeCalendar.setOnClickListener { openDatePicker() }
        homeCalendarText.setOnClickListener { openDatePicker() }
        homeClearBtn.setOnClickListener { openSearch() }
        homeEventsRecyclerView.layoutManager = LinearLayoutManager(context)
        homeEventsRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        homeCalendarText.text =
            SimpleDateFormat("d MMMM, EEEE", Locale.getDefault()).format(Date(selectDate.timeInMillis))
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        presenter.detachView()
    }

    private fun openDatePicker() {
        if (activity != null && isTimeline) {
            val datePicker = DatePickerDialog(
                activity!!, R.style.DatePickerDialog, listenerDatePicker,
                selectDate.get(Calendar.YEAR),
                selectDate.get(Calendar.MONTH),
                selectDate.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = System.currentTimeMillis()
            datePicker.show()
        }
    }

    private fun setInitialDateTime() {
        if (isTimeline) {
            homeCalendarText.text =
                SimpleDateFormat("d MMMM, EEEE", Locale.getDefault()).format(Date(selectDate.timeInMillis))
            homeCurrentTime.visibility = if (isNow()) View.VISIBLE else View.GONE

            if (context != null)
                presenter.getEventsTimeline(
                    context!!,
                    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(selectDate.timeInMillis))
                )
        }
    }

    override fun showTimelineEvents(eventsTimeline: List<EventResponse>) {
        if (eventsTimeline.isEmpty()) {
            //selectDate.timeInMillis = oldDate.timeInMillis
            //setInitialDateTime()
            //showMessage(activity?.resources?.getString(R.string.in_change_day_no_events))
            homeProgressBar.visibility = View.GONE
            homeContent.visibility = View.GONE
            homeContentDayClear.visibility = View.VISIBLE
        } else {
            toggleContentPB(false)
            timer.start()
            adapter.onDataChange(eventsTimeline, isNow())
        }
    }

    override fun showContent(nearDay: TimelineNearDayResponse) {
        if (nearDay.status == "success") {
            /*oldDate.timeInMillis = nearDay.date * 1000
            selectDate.timeInMillis = oldDate.timeInMillis*/
            setInitialDateTime()
        } else {
            isTimeline = false
            homeProgressBar.visibility = View.GONE
            homeContent.visibility = View.GONE
            homeContentClear.visibility = View.VISIBLE
        }
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isTimeline)
            if (isVisiblePB) {
                homeProgressBar.visibility = View.VISIBLE
                homeContent.visibility = View.GONE
                homeContentDayClear.visibility = View.GONE
            } else {
                homeProgressBar.visibility = View.GONE
                homeContent.visibility = View.VISIBLE
                homeContentDayClear.visibility = View.GONE
            }
    }

    private fun openSearch() {
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.selectedItemId = R.id.navigation_search
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.mainNavContainerFragment, SearchFragment())
            .commit()
    }

    private fun openUserBookmarks() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, UserBookmarksFragment())
            .remove(HomeTimelineFragment())
            .commit()
    }

    private fun openUserCourses() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, UserGroupsFragment())
            .remove(HomeTimelineFragment())
            .commit()
    }

    private fun isNow(): Boolean = isOneDay(selectDate, Calendar.getInstance())
    private fun isOneDay(firstDate: Calendar, secondDate: Calendar): Boolean =
        firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR) &&
                firstDate.get(Calendar.DAY_OF_YEAR) == secondDate.get(Calendar.DAY_OF_YEAR)

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        toggleContentPB(false)
    }
}