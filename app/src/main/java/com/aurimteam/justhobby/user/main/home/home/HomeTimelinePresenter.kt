package com.aurimteam.justhobby.user.main.home.home

import android.content.Context
import android.view.View
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.response.TimelineNearDayResponse

class HomeTimelinePresenter(
    private var view: IHomeView?,
    private val model: IHomeModel?
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

    fun getNearDayTimeline(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getNearDayTimeline(token, this)
    }

    fun getEventsTimeline(context: Context, date: String) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getEventsTimeline(token, date, this)
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: IHomeView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}