package com.aurimteam.justhobby.user.search.results

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.BookmarkAddBody
import com.aurimteam.justhobby.response_body.TokenBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class SearchResultModel : ISearchResultModel {
    interface OnFinishedListener {
        fun onResultSuccess(foundedCourses: List<CourseResponseR>, included: IncludedResponse?)
        fun deletedUserBookmark(position: Int)
        fun addedUserBookmark(position: Int)
        fun onResultFail(strError: String?)
    }

    override fun getSearchResultsData(
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
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .getCourses(
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
                token
            ).enqueue(object : Callback<CoursesResponse> {
                override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<CoursesResponse>, response: Response<CoursesResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody.data, responseBody.included)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun deleteUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .deleteBookmark(TokenBody(token), courseId)
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.deletedUserBookmark(position)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun addUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .addBookmark(BookmarkAddBody(token, courseId))
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.addedUserBookmark(position)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}