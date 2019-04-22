package com.aurimteam.justhobby.Main.HomeActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

class HomeMainTimeLinePresenter(private var view: IHomeView?, private val model: IHomeModel?) :
    HomeModel.OnFinishedListener {

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