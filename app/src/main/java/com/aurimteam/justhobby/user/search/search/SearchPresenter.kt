package com.aurimteam.justhobby.user.search.search

import android.content.Context
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.Settings

class SearchPresenter(private var view: ISearchView?, private val model: ISearchModel?, private var context: Context?) :
    SearchModel.OnFinishedListener {

    override fun onResultSuccess(categories: List<CategoryResponse>) {
        view?.setCategories(categories)
    }

    override fun onResultFail(strError: String?) {

    }

    fun getCategories() {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getCategoriesData(token, this)
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