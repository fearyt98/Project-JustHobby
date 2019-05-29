package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import com.aurimteam.justhobby.Response.SubcategoriesResponse

class SearchSubcategoriesModel : ISearchSubcategoriesModel {
    interface onFinishedListener {
        fun onResultSuccess(subcategories: List<SubcategoriesResponse>)
        fun onResultFail()
    }

    override fun getSubcategoriesData(onFinishedListener: onFinishedListener) {
        val subcategories: List<SubcategoriesResponse> = listOf(
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoriesResponse(
                0,
                "languages",
                "Английский"
            )
        )
        onFinishedListener.onResultSuccess(subcategories)
    }
}