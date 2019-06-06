package com.aurimteam.justhobby.user.main.home.home

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.response.TimelineNearDayResponse

class HomeTimelinePresenter(
    private var view: IHomeView?,
    private val model: IHomeModel?,
    private var context: Context?
) : HomeTimelineModel.OnFinishedListener {

    override fun onResultSuccessNearDayTimeline(nearDay: TimelineNearDayResponse) {
        view?.showContent(nearDay)
    }

    override fun onResultSuccessTimeline(eventsTimeline: List<EventResponse>) {
        view?.showTimelineEvents(eventsTimeline)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getNearDayTimeline() {
        if (context != null) {
            val token = Settings(context!!).getProperty("token")
            if (token != null)
                model?.getNearDayTimeline(token, this)
        }
    }

    fun getEventsTimeline(date: String) {
        if (context != null) {
            val token = Settings(context!!).getProperty("token")
            if (token != null) {
                view?.toggleContentPB(true)
                model?.getEventsTimeline(token, date, this)
            }
        }
    }

    fun onDestroy() {
        view = null
    }
}