package com.aurimteam.justhobby.user.search.search

import android.content.Context
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.PriceRangeResponse

class SearchPresenter(private var view: ISearchView?, private val model: ISearchModel?) :
    SearchModel.OnFinishedListener {

    override fun onResultSuccessCategories(categories: List<CategoryResponse>) {
        view?.setCategories(categories)
    }

    override fun onResultSuccessPrice(priceRange: PriceRangeResponse) {
        view?.setPriceRange(priceRange)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    fun getCategories(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getCategoriesData(token, this)
    }

    fun getPriceRange(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getPriceRangeData(token, this)
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ISearchView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}