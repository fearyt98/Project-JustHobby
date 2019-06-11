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

    fun getSearchResults(
        subcategories: String?,
        sortPrice: Int?,
        sortRating: Int?,
        sortLength: Int?,
        priceMax: Int?,
        priceMin: Int?,
        ageMax: Int?,
        ageMin: Int?,
        sex1: Int?,
        sex2: Int?,
        sex3: Int?,
        timetable1: Int?,
        timetable2: Int?,
        timetable3: Int?,
        timetable4: Int?,
        timetable5: Int?,
        timetable6: Int?,
        timetable7: Int?,
        status: Int?,
        query: String?,
        lat: Float?,
        lon: Float?,
        context: Context
    ) {
        val token = Settings(context).getProperty("token")
        if (token != null) {
            view?.toggleContentPB(true)
            model?.getSearchResultsData(
                subcategories,
                sortPrice,
                sortRating,
                sortLength,
                priceMax,
                priceMin,
                ageMax,
                ageMin,
                sex1,
                sex2,
                sex3,
                timetable1,
                timetable2,
                timetable3,
                timetable4,
                timetable5,
                timetable6,
                timetable7,
                status,
                query,
                token,
                lat,
                lon,
                this
            )
        }
    }

    fun isSetView(): Boolean {
        return view != null
    }

    fun attachView(view: ISearchResultView?) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}