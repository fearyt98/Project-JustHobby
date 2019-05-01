package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularPlaces

import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularPlacesModel
import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularPlacesView
import com.aurimteam.justhobby.Response.PopularPlacesResponse

class PopularPlacesPresenter(private var view: IPopularPlacesView?, private val model: IPopularPlacesModel?) :
    PopularPlacesModel.OnFinishedListener {

    override fun onResultSuccess(places: List<PopularPlacesResponse>) {
        view?.showPopularPlaces(places)
    }

    override fun onResultFail() {

    }

    fun getPopularPlaces() {
        model?.getPopularPlacesData(this)
    }

    fun onDestroy() {
        view = null
    }
}