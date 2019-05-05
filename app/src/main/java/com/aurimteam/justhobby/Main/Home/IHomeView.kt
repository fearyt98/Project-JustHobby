package com.aurimteam.justhobby.Main.Home

import com.aurimteam.justhobby.Response.TimelineResponses

interface IHomeView {
    fun showTimeLineEvents(eventsTimeLine: List<TimelineResponses>)
}