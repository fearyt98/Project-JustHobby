package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import com.aurimteam.justhobby.Response.SubcategoryResponse

class SearchSubcategoriesModel : ISearchSubcategoriesModel {
    interface onFinishedListener {
        fun onResultSuccess(subcategories: List<SubcategoryResponse>)
        fun onResultFail()
    }

    override fun getSubcategoriesData(onFinishedListener: onFinishedListener) {
        val subcategories: List<SubcategoryResponse> = listOf(
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            ),
            SubcategoryResponse(
                0,
                "languages",
                "Английский"
            )
        )
        onFinishedListener.onResultSuccess(subcategories)
    }
}