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

class NotificationsService : Service() {

    private var haveNewNotify = false
    private var timer = object : CountDownTimer(3600000, 900000) {
        override fun onTick(millisUntilFinished: Long) {
            checkNewUserNotifications()
            if (haveNewNotify) {
                getNewUserNotifications()
            }
        }

        override fun onFinish() {
            start()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotify()
        checkNewUserNotifications()
        if (haveNewNotify) {
            getNewUserNotifications()
        }
        timer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private fun checkNewUserNotifications() {
        /* App.retrofit
             .create(Api::class.java)
             .checkUserNewNotify(token)
             .enqueue(object : Callback<StatusResponse> {
                 override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                     haveNewNotify = false
                 }
                 override fun onResponse(
                     call: Call<StatusResponse>,
                     response: Response<StatusResponse>
                 ) {
                     val responseBody = response.body()
                     if(response.body() == "success") haveNewNotify = true
                     else haveNewNotify = false
                 }
             })*/
    }

    private fun getNewUserNotifications() {
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
        //showNotify()
    }

    private fun showNotify() {//notifications: List<>notifications: List<String>
        //var count = 0
        val notifintent = Intent(this, HomeTimelineFragment::class.java)
        val pendIntent = PendingIntent.getActivity(this, 0, notifintent, 0)
        val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
       // for (item in notifications) {
            val notify = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Уведомление")
                .setContentText("привет")
                .setContentIntent(pendIntent)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setLights(Color.YELLOW, 3000, 3000)
                .setSmallIcon(R.drawable.ic_notification)
                .build()
            manager.notify(0, notify)
            //count++
       // }
        //count=0
        haveNewNotify = false
    }
}
