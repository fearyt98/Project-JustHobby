package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.SubcategoriesResponse
import com.aurimteam.justhobby.response.SubcategoryResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSubcategoriesModel : ISearchSubcategoriesModel {
    interface OnFinishedListener {
        fun onResultSuccess(subcategories:  List<SubcategoryResponse>)
        fun onResultFail(strError: String?)
    }

    override fun getSubcategoriesData(token: String, categoryId: Int, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getSubcategories(categoryId, token)
            .enqueue(object : Callback<SubcategoriesResponse> {
                override fun onFailure(call: Call<SubcategoriesResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<SubcategoriesResponse>, response: Response<SubcategoriesResponse>) {
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