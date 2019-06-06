package com.aurimteam.justhobby.user.main.home.user_courses

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.GroupsResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCoursesModel : IUserCoursesModel {
    interface OnFinishedListener {
        fun onResultSuccess(userCourses: List<CategoryResponse>, included: List<CourseResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getUserCoursesData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getUserGroups(token)
            .enqueue(object : Callback<GroupsResponse> {
                override fun onFailure(call: Call<GroupsResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }
                override fun onResponse(call: Call<GroupsResponse>, response: Response<GroupsResponse>) {
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
}