package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesFragment

import com.aurimteam.justhobby.Response.CourseResponse
import com.aurimteam.justhobby.Response.IdentifierResponse
import com.aurimteam.justhobby.Response.SubcategoriesResponse
import com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesFragment.ISearchSubcategoriesModel
import java.sql.Timestamp

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