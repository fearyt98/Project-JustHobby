package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces

import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularPlaces.PopularPlacesModel

interface IPopularPlacesModel {
    fun getPopularPlacesData(onFinishedListener: PopularPlacesModel.OnFinishedListener)
}