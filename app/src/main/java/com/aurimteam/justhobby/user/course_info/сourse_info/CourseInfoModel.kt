package com.aurimteam.justhobby.user.course_info.сourse_info

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.BookmarkAddBody
import com.aurimteam.justhobby.response_body.GroupAddBody
import com.aurimteam.justhobby.response_body.TokenBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseInfoModel : ICourseInfoModel {
    interface OnFinishedListener {
        fun onResultSuccessCourse(course: CourseResponseOneR)
        fun onResultSuccessCourseGroups(groups: List<GroupResponse>)
        fun onResultFail(strError: String?)
        fun deletedUserGroup(position: Int)
        fun addedUserGroup(position: Int)
        fun deletedUserBookmark()
        fun addedUserBookmark()
    }

    override fun getCourseGroupsData(token: String, courseId: Long, onFinishedListener: OnFinishedListener) {

            App.retrofit
                .create(Api::class.java)
                .getGroupsOneCourse(courseId, token)
                .enqueue(object : Callback<GroupsResponse> {
                    override fun onFailure(call: Call<GroupsResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(call: Call<GroupsResponse>, response: Response<GroupsResponse>) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessCourseGroups(responseBody.data)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })

    }

    override fun getCourseData(token: String, courseId: Long, onFinishedListener: OnFinishedListener) {

            App.retrofit
                .create(Api::class.java)
                .getOneCourse(courseId, token)
                .enqueue(object : Callback<CourseResponseOneR> {
                    override fun onFailure(call: Call<CourseResponseOneR>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(call: Call<CourseResponseOneR>, response: Response<CourseResponseOneR>) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessCourse(responseBody)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })

    }

    override fun deleteUserGroup(
        token: String,
        groupId: Long,
        position: Int,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .deleteGroup(TokenBody(token), groupId)
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.deletedUserGroup(position)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun addUserGroup(
        token: String,
        groupId: Long,
        position: Int,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .addGroup(GroupAddBody(token, groupId))
            .enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.addedUserGroup(position)
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
                        onFinishedListener.deletedUserBookmark()
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
                        onFinishedListener.addedUserBookmark()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}