package com.aurimteam.justhobby.start.registry.start

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.UserResponse
import com.aurimteam.justhobby.response_body.UpdateUserBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegistryStartModel : IRegistryStartModel {
    interface OnFinishedListener {
        fun onResultSuccess()
        fun onResultFail(error: String?)
    }

    override fun sendUserImage(token: String, filePath: Uri, context: Context, onFinishedListener: OnFinishedListener) {
        val file = File(getPath(filePath, context))
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val multipartBodyPart = MultipartBody.Part.createFormData("avatar", file.name, requestBody)
        val requestBodyDescription = RequestBody.create(MediaType.parse("text/plain"), "image-type")
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
                            onFinishedListener.onResultSuccess()
                        } else {
                            val jsonObj = JSONObject(response.errorBody()?.string())
                            onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message").toString())
                        }
                    }
                })

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

    override fun sendUserInfoData(
        token: String,
        first_name: String,
        last_name: String,
        onFinishedListener: OnFinishedListener
    ) {
        App.retrofit
            .create(Api::class.java)
            .updateUser(UpdateUserBody(token, first_name, last_name))
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFinishedListener.onResultFail(t.message)
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        onFinishedListener.onResultSuccess()
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
}