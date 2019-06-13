package com.aurimteam.justhobby.user.main.home.home

interface IHomeModel {
    fun getEventsTimeline(token: String, date: String, onFinishedListener: HomeTimelineModel.OnFinishedListener)
    fun getNearDayTimeline(isNext: Boolean, token: String, onFinishedListener: HomeTimelineModel.OnFinishedListener)
}