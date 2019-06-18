package com.aurimteam.justhobby.user.main.settings.user_profile

import android.content.Context
import android.net.Uri
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
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class UserProfileModel : IUserProfileModel {
    interface OnFinishedListener {
        fun onResultSuccess(user: UserResponse)
        fun userInfoSended()
        fun userPasswordSended()
        fun onResultSuccessSuggest(data: List<SuggestResponse>)
        fun onResultFail(strError: String?)
    }

    override fun sendUserImage(token: String, filePath: Uri, context: Context, onFinishedListener: OnFinishedListener) {
        val file = File(getPath(filePath, context))
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val multipartBodyPart = MultipartBody.Part.createFormData("avatar", file.name, requestBody)
        val requestBodyDescription = RequestBody.create(MediaType.parse("text/plain"), "image-type")

        GlobalScope.launch(Dispatchers.IO) {
            App.retrofit
                .create(Api::class.java)
                .uploadUserImage(token, requestBodyDescription, multipartBodyPart)
                .enqueue(object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
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
    }

    private fun getPath(uri: Uri, context: Context): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null) ?: return null
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s = cursor.getString(columnIndex)
        cursor.close()
        return s
    }

    override fun getUserInfoData(token: String, onFinishedListener: OnFinishedListener) {
        App.retrofit
            .create(Api::class.java)
            .getUser(token)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess(responseBody)
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
                    null,
                    null,
                    null,
                    address
                )
            )
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.userInfoSended()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())

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
                        }
                    }
                }
            })
    }

    override fun sendUserPassword(
        token: String,
        password_old: String?,
        password: String?,
        password_confirmation: String?,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .updateAllUserInfo(
                UpdateUserAllBody(
                    token,
                    null,
                    null,
                    password_old,
                    password,
                    password_confirmation,
                    null
                )
            )
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.userPasswordSended()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()?.string())

                        if (jsonObj.getJSONObject("error")?.getString("message") == "Old password incorrect")
                            onFinishedListener.onResultFail("IncorrectOldPass")
                        else {
                            if (jsonObj.getJSONObject("error")?.has("description") != null) {
                                val description = jsonObj.getJSONObject("error")?.getJSONObject("description")!!
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
        GlobalScope.launch(Dispatchers.IO) {
            App.retrofit
                .create(Api::class.java)
                .getSuggests(token, query)
                .enqueue(object : Callback<SuggestsResponse> {
                    override fun onFailure(call: Call<SuggestsResponse>, t: Throwable) {
                        onFinishedListener.onResultFail(t.message)
                    }

                    override fun onResponse(
                        call: Call<SuggestsResponse>,
                        response: Response<SuggestsResponse>
                    ) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onFinishedListener.onResultSuccessSuggest(responseBody.data)
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error").getString("message").toString())
                        }
                    }
                })
        }
    }
}

