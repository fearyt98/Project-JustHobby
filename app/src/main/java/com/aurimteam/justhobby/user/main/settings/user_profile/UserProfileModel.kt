package com.aurimteam.justhobby.user.main.settings.user_profile

import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.SuggestResponse
import com.aurimteam.justhobby.response.SuggestsResponse
import com.aurimteam.justhobby.response.UserResponse
import com.aurimteam.justhobby.response_body.UpdateUserAllBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserProfileModel : IUserProfileModel {
    interface OnFinishedListener {
        fun onResultSuccess(email: String, name: String, lastName: String, address: String?, image: String?)
        fun userInfoSended()
        fun onSuggestResultSuccess(data: List<SuggestResponse>)
        fun onResultFail(strError: String)
    }

    override fun sendUserImage(token: String, filePath: String?, onFinishedListener: OnFinishedListener) {
        val file = File(filePath)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val multipartBodyPart = MultipartBody.Part.createFormData("avatar", file.name, requestBody)
        val requestBodyDescription = RequestBody.create(MediaType.parse("text/plain"), "image-type")

        App.retrofit
            .create(Api::class.java)
            .uploadUserImage(token, requestBodyDescription, multipartBodyPart)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.userInfoSended()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message").toString())
                    }
                }
            })
    }

    override fun getUserInfoData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getUser(token)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(
                            responseBody.attributes.email, responseBody.attributes.first_name,
                            responseBody.attributes.last_name, responseBody.attributes.address,
                            responseBody.attributes.avatar)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                    }
                }
            })
    }

    override fun sendUserInfoData(
        token: String,
        first_name: String,
        last_name: String,
        password_old: String?,
        password: String?,
        password_confirmation: String?,
        address: String?,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .updateAllUserInfo(
                UpdateUserAllBody(
                    token,
                    first_name,
                    last_name,
                    password_old,
                    password,
                    password_confirmation,
                    address
                )
            )
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.userInfoSended()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())

                        if (jsonObj.getJSONObject("error")?.getString("message") == "Old password incorrect")
                            onFinishedListener.onResultFail("IncorrectOldPass")
                        else {
                            if (jsonObj.getJSONObject("error")?.has("description") != null) {
                                val description = jsonObj.getJSONObject("error")?.getJSONObject("description")!!
                                if (description.has("first_name")) {
                                    if (description.getJSONObject("first_name").has("Regex"))
                                        onFinishedListener.onResultFail("regexFirstName")
                                }
                                if (description.has("last_name")) {
                                    if (description.getJSONObject("last_name").has("Regex"))
                                        onFinishedListener.onResultFail("regexLastName")
                                }
                                if (description.has("password")) {
                                    if (description.getJSONObject("password").has("Regex"))
                                        onFinishedListener.onResultFail("IncorrectPass")
                                }
                            }
                        }
                    }
                }
            })
    }

    override fun getSuggests(token: String, query: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getSuggests(token, query)
            .enqueue(object : Callback<SuggestsResponse> {
                override fun onFailure(call: Call<SuggestsResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Error of parsing")
                }

                override fun onResponse(
                    call: Call<SuggestsResponse>,
                    response: Response<SuggestsResponse>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onSuggestResultSuccess(responseBody.data)
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())
                        onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                    }
                }
            })
    }
}

