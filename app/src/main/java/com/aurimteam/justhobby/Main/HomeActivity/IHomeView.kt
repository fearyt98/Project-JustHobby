package com.aurimteam.justhobby.Main.HomeActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponse

interface IHomeView {
    fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponse>)
}