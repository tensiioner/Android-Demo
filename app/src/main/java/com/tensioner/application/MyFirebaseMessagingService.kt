package com.tensioner.application

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.R
import android.R.attr
import android.accessibilityservice.GestureDescription
import android.annotation.SuppressLint

import android.app.PendingIntent

import android.content.Intent

import android.app.NotificationManager
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import android.R.attr.bitmap




 class MyFirebaseMessagingService : FirebaseMessagingService() {
  private lateinit var intent : Intent

    override fun onNewToken(token: String) {
        Log.d("FirebaseToken","RefreshedToken: $token")
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "Token", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("TokenID", token)
        editor.apply()
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
    // ...
        val data = remoteMessage.data

        //you can get your text message here.

        //you can get your text message here.
        val text = data["text"]

    // TODO(developer): Handle FCM messages here.
    // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
    Log.d(TAG, "From: ${remoteMessage.from}")

    // Check if message contains a data payload.
    if (remoteMessage.data.isNotEmpty()) {
        Log.d(TAG, "Message data payload: ${remoteMessage.data}")

        if (/* Check if data needs to be processed by long running job */ true) {
            // For long-running tasks (10 seconds or more) use WorkManager.
            //scheduleJob()
        } else {
            // Handle message within 10 seconds
            //handleNow()
        }
    }

    // Check if message contains a notification payload.
    remoteMessage.notification?.let {
        Log.d(TAG, "Message Notification Body: ${it.body}")

    }

    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["body"]
        val image = remoteMessage.data["image"]

        val imageBitmap: Bitmap =
            Glide.with(this).asBitmap().load(image).submit().get()

        when(remoteMessage.data["click_action"])
        {
            "AfterLogin"->{
                intent = Intent(this, AfterLogin::class.java)

            }
            "RegisterActivity"->{
                intent = Intent(this, RegisterActivity::class.java)


            }
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationBuilder = NotificationCompat.Builder(this)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(message)
        notificationBuilder.setSmallIcon(android.R.drawable.ic_popup_reminder)
        notificationBuilder.setLargeIcon(imageBitmap)
        notificationBuilder.setAutoCancel(true)
        val style = NotificationCompat.BigPictureStyle()
        style.bigPicture(imageBitmap)
        notificationBuilder.setStyle(style)

        notificationBuilder.setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())



    }






}