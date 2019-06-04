package com.aurimteam.justhobby.user.main.home.home

import com.aurimteam.justhobby.response.EventResponse

interface IHomeView {
    fun showTimeLineEvents(eventsTimeLine: List<EventResponse>)
    fun showMessage(message: String?)
}