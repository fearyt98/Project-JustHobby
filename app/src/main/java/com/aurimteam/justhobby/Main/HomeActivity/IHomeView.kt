package com.aurimteam.justhobby.Main.HomeActivity

import com.aurimteam.justhobby.Response.TimelineResponses

interface IHomeView {
    fun showTimeLineEvents(eventsTimeLine: List<TimelineResponses>)
}