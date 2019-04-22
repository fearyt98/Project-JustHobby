package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

class HomeMainTimeLinePresenter(private var view: IHomeMainView?, private val model: IHomeMainModel?) :
    HomeMainTimeLineModel.OnFinishedListener {

    override fun onResultFail() {
    }

    override fun onResultSuccess(eventsTimeLine: List<TimeLineEventResponse>) {
        view?.showTimeLineEvents(eventsTimeLine)
    }

    fun getEventsTimeLine() {
        model?.getEventsTimeLine(this)
    }

    fun onDestroy() {
        view = null
    }

}