package com.aurimteam.justhobby

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.app.PendingIntent
import com.aurimteam.justhobby.user.main.home.home.HomeTimelineFragment
import android.app.NotificationManager
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import com.aurimteam.justhobby.App.Companion.CHANNEL_ID
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsService : Service() {

    private var token: String? = null
    private var timer = object : CountDownTimer(3600000, 900000) {
        override fun onTick(millisUntilFinished: Long) {
            checkNewUserNotifications()
        }
        override fun onFinish() {
            start()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setToken(intent!!.getStringExtra("token"))
        checkNewUserNotifications()
        timer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private fun setToken(token: String) {
        this.token = token
    }

    private fun checkNewUserNotifications() {
        if (token != null)
            App.retrofit
                .create(Api::class.java)
                .checkUserNewNotify(token.toString())
                .enqueue(object : Callback<StatusResponse> {
                    override fun onFailure(call: Call<StatusResponse>, t: Throwable) {}

                    override fun onResponse(
                        call: Call<StatusResponse>,
                        response: Response<StatusResponse>
                    ) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.status == "success") {
                            getNewUserNotifications()
                        }
                    }
                })
    }

    private fun getNewUserNotifications() {
        if (token != null)
            App.retrofit
                .create(Api::class.java)
                .getUserNotify(token.toString(), false)
                .enqueue(object : Callback<NotificationsResponse> {
                    override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {}

                    override fun onResponse(
                        call: Call<NotificationsResponse>,
                        response: Response<NotificationsResponse>
                    ) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            showNotify(responseBody)
                        }
                    }
                })
    }

    private fun showNotify(notifications: NotificationsResponse) {
        if (notifications != null && notifications.data.isNotEmpty() && notifications.included != null) {
            var count = 0
            val courseIncludedList: MutableMap<Long, CourseResponseR> = mutableMapOf()
            val notifyIntent = Intent(this, MainNavActivity::class.java)
            notifyIntent.putExtra("openNotify",true)
            val pendIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0)
            val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notifications.included.courses!!.isNotEmpty())
                for (item in notifications.included.courses) {
                    courseIncludedList[item.id] = item
                }
            for (item in notifications.data) {
                if (courseIncludedList[item.relationships.course.id] != null) {
                    val notify = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle(courseIncludedList[item.relationships.course.id]!!.attributes.title)
                        .setContentText(item.attributes.text)
                        .setContentIntent(pendIntent)
                        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                        .setLights(Color.YELLOW, 3000, 3000)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setStyle(NotificationCompat.BigTextStyle().bigText(item.attributes.text))
                        .build()
                    manager.notify(count, notify)
                    count++
                }
            }
        }
    }
}
