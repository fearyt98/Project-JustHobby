package com.aurimteam.justhobby.user.search.results

interface ISearchResultModel {
    fun getSearchResultsData(
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
        token: String,
        lat: Float?,
        lon: Float?,
        onFinishedListener: SearchResultModel.OnFinishedListener
    )

    fun deleteUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        onFinishedListener: SearchResultModel.OnFinishedListener
    )

    fun addUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        onFinishedListener: SearchResultModel.OnFinishedListener
    )
}