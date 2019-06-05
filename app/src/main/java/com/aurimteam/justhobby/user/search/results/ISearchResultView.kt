package com.aurimteam.justhobby.user.search.results

import com.aurimteam.justhobby.response.CourseResponse

interface ISearchResultView {
    fun showSearchResults(foundedCourses: List<CourseResponse>)
}