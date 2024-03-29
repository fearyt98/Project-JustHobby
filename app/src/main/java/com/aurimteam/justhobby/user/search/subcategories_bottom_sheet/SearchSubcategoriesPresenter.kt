package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.SubcategoryResponse

class SearchSubcategoriesPresenter(
    private var view: ISearchSubcategoriesView?,
    private val model: ISearchSubcategoriesModel?
) :
    SearchSubcategoriesModel.OnFinishedListener {

    override fun onResultSuccess(subcategories: List<SubcategoryResponse>) {
        view?.showSubcategories(subcategories)
    }

    override fun onResultFail(strError: String?) {

    }

    fun getSubcategories(context: Context, categoryId: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getSubcategoriesData(token,categoryId, this)
        }
    }

    fun checked(state: Int) {
        view?.changeCheckBox(state)
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ISearchSubcategoriesView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}