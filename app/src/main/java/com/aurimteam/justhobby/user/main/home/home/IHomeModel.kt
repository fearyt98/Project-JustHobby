package com.aurimteam.justhobby.user.main.home.home

interface IHomeModel {
    fun getEventsTimeLine(Token: String, date: String, onFinishedListener: HomeTimeLineModel.OnFinishedListener)
}