package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesFragment

import com.aurimteam.justhobby.Response.SubcategoryResponse

class SearchSubcategoriesPresenter(private var view: ISearchSubcategoriesView?, private val model: ISearchSubcategoriesModel?) :
    SearchSubcategoriesModel.onFinishedListener {

    override fun onResultSuccess(subcategories: List<SubcategoryResponse>) {
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