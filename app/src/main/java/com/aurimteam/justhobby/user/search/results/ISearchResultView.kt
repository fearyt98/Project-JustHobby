package com.aurimteam.justhobby.user.search.results

import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface ISearchResultView {
    fun showSearchResults(foundedCourses: List<CourseResponseR>, included: IncludedResponse?)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserBookmark(position: Int)
    fun addedUserBookmark(position: Int)
}