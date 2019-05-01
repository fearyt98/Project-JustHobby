package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Response.PopularPlacesResponse

interface IPopularPlacesView {
    fun showPopularPlaces(places: List<PopularPlacesResponse>)
}