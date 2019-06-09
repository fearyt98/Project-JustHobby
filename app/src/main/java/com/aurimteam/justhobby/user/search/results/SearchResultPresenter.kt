package com.aurimteam.justhobby.user.search.results

import android.content.Context
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.course.ICoursePresenter
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

class SearchResultPresenter(private var view: ISearchResultView?, private val model: ISearchResultModel?) :
    SearchResultModel.OnFinishedListener, ICoursePresenter {

    override fun onResultSuccess(foundedCourses: List<CourseResponseR>, included: IncludedResponse?) {
        view?.showSearchResults(foundedCourses, included)
    }

    override fun onResultFail(strError: String?) {
        view?.showMessage(strError)
    }

    override fun deletedUserBookmark(position: Int) {
        view?.deletedUserBookmark(position)
    }

    override fun addedUserBookmark(position: Int) {
        view?.addedUserBookmark(position)
    }

    override fun deleteUserBookmark(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.deleteUserBookmark(token, courseId, position, this)
    }

    override fun addUserBookmark(context: Context, courseId: Long, position: Int) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.addUserBookmark(token, courseId, position, this)
    }

    fun getSearchResults(context: Context) {
        val token = Settings(context).getProperty("token")
        if (token != null)
            model?.getSearchResultsData(token, this)
    }

    fun onDestroy() {
        view = null
    }
}