package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import com.aurimteam.justhobby.response.SubcategoryResponse

class SearchSubcategoriesModel : ISearchSubcategoriesModel {
    interface OnFinishedListener {
        fun onResultSuccess(subcategories: List<SubcategoryResponse>)
        fun onResultFail()
    }

    override fun getSubcategoriesData(onFinishedListener: OnFinishedListener) {
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