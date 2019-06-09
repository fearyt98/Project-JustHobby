package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.ReviewResponse
import com.aurimteam.justhobby.response.ReviewsResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseReviewsModel : ICourseReviewsModel {
    interface OnFinishedListener {
        fun onResultSuccess(courseReviews: List<ReviewResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getCourseReviewsData(token: String, courseId: Long, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getReviewsOneCourse(courseId, token)
            .enqueue(object : Callback<ReviewsResponse> {
                override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody.data)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}