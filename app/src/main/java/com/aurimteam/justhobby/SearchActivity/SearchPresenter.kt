package com.aurimteam.justhobby.SearchActivity

import android.content.Context
import com.aurimteam.justhobby.Response.CategoryResponse
import com.aurimteam.justhobby.Settings

class SearchPresenter(private var view: ISearchView?, private val model: ISearchModel?, private var context: Context?) :
    SearchModel.onFinishedListener {

    override fun onResultSuccess(categories: List<CategoryResponse>) {
        view?.setCategories(categories)
    }

    override fun onResultSuccess(number: Int) {

    }

    override fun onResultFail(strError: String) {

    }

    fun getCategories() {
        val token = Settings(context!!).getProperty("token")
        if (token != null)
            model?.getCategoriesData(token, this)
    }

    fun onDestroy() {
        view = null
    }
}