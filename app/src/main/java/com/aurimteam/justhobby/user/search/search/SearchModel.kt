package com.aurimteam.justhobby.user.search.search

import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.response.CategoriesResponse
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.response.PriceRangeResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel : ISearchModel {
    interface OnFinishedListener {
        fun onResultSuccessCategories(categories: List<CategoryResponse>)
        fun onResultSuccessPrice(priceRange: PriceRangeResponse)
        fun onResultFail(strError: String?)
    }

    override fun getCategoriesData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getCategories(token, 12, 1)
            .enqueue(object : Callback<CategoriesResponse> {
                override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccessCategories(responseBody.data)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }

    override fun getPriceRangeData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getPriceRange(token)
            .enqueue(object : Callback<PriceRangeResponse> {
                override fun onFailure(call: Call<PriceRangeResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<PriceRangeResponse>, response: Response<PriceRangeResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccessPrice(responseBody)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                    }
                }
            })
    }
}