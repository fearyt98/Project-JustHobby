package com.aurimteam.justhobby.Main.Home

import com.aurimteam.justhobby.Response.TimelineResponses

class HomeMainTimeLinePresenter(private var view: IHomeView?, private val model: IHomeModel?) :
    HomeTimeLineModel.OnFinishedListener {

    override fun onResultFail() {
    }

    override fun onResultSuccess(eventsTimeLine: List<TimelineResponses>) {
        view?.showTimeLineEvents(eventsTimeLine)
    }

    fun getEventsTimeLine() {
        model?.getEventsTimeLine(this)
    }

    fun onDestroy() {
        view = null
    }

}