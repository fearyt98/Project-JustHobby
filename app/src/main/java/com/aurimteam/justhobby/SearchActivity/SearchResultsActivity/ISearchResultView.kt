package com.aurimteam.justhobby.SearchActivity.SearchResultsActivity

import com.aurimteam.justhobby.Response.CourseResponse

interface ISearchResultView {
    fun showSearchResults(foundedCourses: List<CourseResponse>)
}