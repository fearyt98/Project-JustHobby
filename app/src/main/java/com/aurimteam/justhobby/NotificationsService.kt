package com.aurimteam.justhobby

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import com.aurimteam.justhobby.api.Api
import com.aurimteam.justhobby.response.NewNotificationsStatus
import com.aurimteam.justhobby.response.TimelineNearDayResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.PendingIntent
import com.aurimteam.justhobby.user.main.home.home.HomeTimelineFragment
import android.app.NotificationManager
import com.aurimteam.justhobby.user.main.settings.notifications.NotificationsFragament

class NotificationsService(private val token: String, private val context: Context) : Service() {

    private var haveNewNotify = false
    private var timer = object : CountDownTimer(900000, 899000) {
        override fun onTick(millisUntilFinished: Long) {
            checkNewUserNotifications()
        }

        override fun onFinish() {
           // if (haveNewNotify) getNewUserNotifications()
            start()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getNewUserNotifications(intent)
        //timer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private fun checkNewUserNotifications() {
        App.retrofit
            .create(Api::class.java)
            .checkUserNewNotify(token)
            .enqueue(object : Callback<NewNotificationsStatus> {
                override fun onFailure(call: Call<NewNotificationsStatus>, t: Throwable) {
                    haveNewNotify = false
                }

                override fun onResponse(
                    call: Call<NewNotificationsStatus>,
                    response: Response<NewNotificationsStatus>
                ) {
                    val responseBody = response.body()
                    haveNewNotify = responseBody != null
                }
            })
    }

    private fun getNewUserNotifications(intent: Intent?) {
        /* App.retrofit
             .create(Api::class.java)
             .getNearDayTimeline(token)
             .enqueue(object : Callback<TimelineNearDayResponse> {
                 override fun onFailure(call: Call<TimelineNearDayResponse>, t: Throwable) {
                     //onFinishedListener.onResultFail(t.message)
                 }
                 override fun onResponse(call: Call<TimelineNearDayResponse>, response: Response<TimelineNearDayResponse>) {
                     val responseBody = response.body()
                     if (responseBody != null) {
                         //onFinishedListener.onResultSuccessNearDayTimeline(responseBody)
                     } else {
                         val jsonObj = JSONObject(response.errorBody()?.string())
                        // onFinishedListener.onResultFail(jsonObj.getJSONObject("error")?.getString("message")?.toString())
                     }
                 }
             })*/
        showNotify(intent)
    }

    private fun showNotify(intent: Intent?) {
        val pIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notification = Notification.Builder(context)
            .setContentTitle("Привет")
            .setContentText("пока")
            //.setSmallIcon()
            .setContentIntent(pIntent)
            .setAutoCancel(true)
            .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }
}
