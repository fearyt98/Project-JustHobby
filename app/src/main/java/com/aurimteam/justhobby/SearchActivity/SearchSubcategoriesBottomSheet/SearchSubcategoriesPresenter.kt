package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import com.aurimteam.justhobby.Response.SubcategoriesResponse

class SearchSubcategoriesPresenter(private var view: ISearchSubcategoriesView?, private val model: ISearchSubcategoriesModel?) :
    SearchSubcategoriesModel.onFinishedListener {

    override fun onResultSuccess(subcategories: List<SubcategoriesResponse>) {
        view?.showSubcategories(subcategories)
    }

    override fun onResultFail() {

    }

    fun getSubcategories(){
        model?.getSubcategoriesData(this)
    }

    fun onDestroy(){
        view = null
    }
}