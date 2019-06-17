package com.aurimteam.justhobby.user.company_info.company_info

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.BookmarkAddBody
import com.aurimteam.justhobby.response_body.TokenBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyInfoModel : ICompanyInfoModel {
    interface OnFinishedListener{
        fun onResultSuccessCompany(company: CompanyResponseOneR)
        fun onResultSuccessCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?)
        fun onResultFail(strError: String?)
        fun deletedUserBookmark(position: Int)
        fun addedUserBookmark(position: Int)
    }

    override fun getCompanyCoursesData(token: String, companyId: Long, lat: Float?, lon: Float?,  onFinishedListener: OnFinishedListener) {
        GlobalScope.launch(Dispatchers.IO) {
            App.retrofit
                .create(Api::class.java)
                .getCoursesOneCompany(companyId, token, lat, lon)
                .enqueue(object : Callback<CoursesResponse> {
                    override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(call: Call<CoursesResponse>, response: Response<CoursesResponse>) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessCompanyCourses(responseBody.data, responseBody.included)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })
        }
    }

    override fun getCompanyData(token: String, companyId: Long, onFinishedListener: OnFinishedListener) {
        GlobalScope.launch(Dispatchers.IO) {
            App.retrofit
                .create(Api::class.java)
                .getOneCompany(companyId, token)
                .enqueue(object : Callback<CompanyResponseOneR> {
                    override fun onFailure(call: Call<CompanyResponseOneR>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(call: Call<CompanyResponseOneR>, response: Response<CompanyResponseOneR>) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessCompany(responseBody)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                        }
                    }
                })
        }
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