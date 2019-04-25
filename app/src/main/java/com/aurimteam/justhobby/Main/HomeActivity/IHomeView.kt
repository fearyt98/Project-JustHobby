package com.aurimteam.justhobby.Main.HomeActivity

import com.aurimteam.justhobby.Response.TimeLineEventResponses

interface IHomeView {
    fun showTimeLineEvents(eventsTimeLine: List<TimeLineEventResponses>)
}