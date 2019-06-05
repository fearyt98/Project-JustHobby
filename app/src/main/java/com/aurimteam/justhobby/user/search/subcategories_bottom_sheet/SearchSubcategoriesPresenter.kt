package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import com.aurimteam.justhobby.response.SubcategoryResponse

class SearchSubcategoriesPresenter(private var view: ISearchSubcategoriesView?, private val model: ISearchSubcategoriesModel?) :
    SearchSubcategoriesModel.OnFinishedListener {

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