package com.aurimteam.justhobby.user.main.home.user_bookmarks

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import com.aurimteam.justhobby.response.StatusResponse
import com.aurimteam.justhobby.response_body.TokenBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class UserBookmarksModel : IUserBookmarksModel {
    interface OnFinishedListener {
        fun onResultSuccess(bookmarks: List<CourseResponse>)
        fun deletedUserBookmark(position: Int)
        fun onResultFail(strError: String?)
    }

    override fun getUserBookmarksData(OnFinishedListener: OnFinishedListener) {
        val bookmarks: List<CourseResponse> = listOf()
        App.retrofit
            .create(Api::class.java)
            .getUserBookmarks(TokenBody(token))
            .enqueue(object : Callback<CourseResponse> {
                override fun onFailure(call: Call<CourseResponse>, t: Throwable) {
                    OnFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<CourseResponse>, response: Response<CourseResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        OnFinishedListener.onResultSuccess(responseBody)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        OnFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
        OnFinishedListener.onResultSuccess(bookmarks)
    }

    override fun deleteUserBookmarkData(
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
}