package com.aurimteam.justhobby.user.main.home.home

import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.response.TimelineNearDayResponse

interface IHomeView {
    fun showTimelineEvents(eventsTimeline: List<EventResponse>)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun showContent(nearDay: TimelineNearDayResponse)
}