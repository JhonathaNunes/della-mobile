package com.della.della_mobile.services

import android.content.Intent
import android.util.Log
import com.della.della_mobile.AddActivity
import com.della.della_mobile.LoginActivity
import com.della.della_mobile.R
import com.della.della_mobile.utils.NotificationUtil
import com.della.della_mobile.utils.Prefs
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    val TAG = "FIREBASE_TEST"

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, token)
        Prefs.setString("FB_TOKEN", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification !== null) {
            showNotification(remoteMessage)
        }
    }

    private fun showNotification(remoteMessage: RemoteMessage) {
        var intent = Intent(this, LoginActivity::class.java)

        val title = remoteMessage.notification?.title ?: getString(R.string.app_name)
        val body = remoteMessage.notification?.body ?: "Just testing"

        if (remoteMessage.data.isNotEmpty()) {
            intent = Intent(this, AddActivity::class.java)
            val clientId = remoteMessage.data["clientId"]?.toLong() ?: -1
            val client = ClientsService.getClient(clientId)

            intent.putExtra("CLIENT", client)
        }

        NotificationUtil.create(1, intent, title, body)
    }
    
}