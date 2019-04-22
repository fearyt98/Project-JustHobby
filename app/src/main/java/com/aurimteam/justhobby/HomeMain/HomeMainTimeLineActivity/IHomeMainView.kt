package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

interface IHomeMainView {
    fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponse>)
}