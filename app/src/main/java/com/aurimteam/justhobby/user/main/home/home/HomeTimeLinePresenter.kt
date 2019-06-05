package com.aurimteam.justhobby.user.main.home.home

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.EventResponse

class HomeTimeLinePresenter(
    private var view: IHomeView?,
    private val model: IHomeModel?,
    private var context: Context?
) :
    HomeTimeLineModel.OnFinishedListener {

    override fun onResultSuccess(eventsTimeline: List<EventResponse>) {
        view?.showTimeLineEvents(eventsTimeline)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getEventsTimeLine(date: String) {
        val token = Settings(context!!).getProperty("token")
        if (token != null) {
            view?.toggleContentPB()
            model?.getEventsTimeLine(token, date, this)
        }
    }

    fun onDestroy() {
        view = null
    }
}