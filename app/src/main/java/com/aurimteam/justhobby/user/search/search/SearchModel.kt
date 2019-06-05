package com.aurimteam.justhobby.user.search.search

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response.CategoriesResponse
import com.aurimteam.justhobby.response.CategoryResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel : ISearchModel {
    interface OnFinishedListener {
        fun onResultSuccess(categories: List<CategoryResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getCategoriesData(Token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getCategories(Token, 12, 1)
            .enqueue(object : Callback<CategoriesResponse> {
                override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
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