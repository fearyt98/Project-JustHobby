package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.BookmarkAddBody
import com.aurimteam.justhobby.response_body.TokenBody
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class PopularCoursesModel : IPopularCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(courses: List<CourseResponseR>, included: IncludedResponse?)
        fun deletedUserBookmark(position: Int)
        fun addedUserBookmark(position: Int)
        fun onResultFail(strError: String?)
    }

    override fun getPopularCoursesData(token: String, OnFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getPopularCourses(token)
            .enqueue(object : Callback<CoursesResponse> {
                override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                    OnFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<CoursesResponse>, response: Response<CoursesResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        OnFinishedListener.onResultSuccess(responseBody.data, responseBody.included)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        OnFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun deleteUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        OnFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .deleteBookmark(TokenBody(token), courseId)
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    OnFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        OnFinishedListener.deletedUserBookmark(position)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        OnFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun addUserBookmark(
        token: String,
        courseId: Long,
        position: Int,
        OnFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .addBookmark(BookmarkAddBody(token, courseId))
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    OnFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        OnFinishedListener.addedUserBookmark(position)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        OnFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}