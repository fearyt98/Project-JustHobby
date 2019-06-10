package com.aurimteam.justhobby.user.course_info.course_review_new

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.StatusResponse
import com.aurimteam.justhobby.response_body.ReviewAddBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseReviewNewModel : ICourseReviewNewModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail(strError: String?)
    }

    override fun sendNewReviewData(
        courseId: Long,
        review: String,
        rating: Int,
        token: String,
        onFinishedListener: OnFinishedListener
    ) { App.retrofit
        .create(Api::class.java)
        .addReviewOneCourse(courseId, ReviewAddBody(token, review, rating))
        .enqueue(object : Callback<StatusResponse> {
            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message)
            }

            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    onFinishedListener.onResultSuccess()
                } else {
                    val jsonObj = JSONObject(response.errorBody()?.string())
                    onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                }
            }
        })
    }

}