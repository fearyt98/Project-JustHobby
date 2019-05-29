package com.aurimteam.justhobby.SearchActivity

import com.aurimteam.justhobby.Api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.Response.CategoriesResponse
import com.aurimteam.justhobby.Response.CategoryResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel : ISearchModel {
    interface onFinishedListener {
        fun onResultSuccess(categories: List<CategoryResponse>)
        fun onResultSuccess(number: Int)
        fun onResultFail(strError: String)
    }

    override fun getCategoriesData(Token: String, onFinishedListener: onFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getCategories(Token, 12, 1)
            .enqueue(object : Callback<CategoriesResponse> {
                override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                    val categoriesResponse = response.body()
                    if (categoriesResponse != null) {
                        onFinishedListener.onResultSuccess(categoriesResponse.data)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getString("message").toString())
                    }
                }
            })
    }
}