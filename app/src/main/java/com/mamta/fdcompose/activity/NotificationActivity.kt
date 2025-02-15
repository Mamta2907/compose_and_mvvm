package com.mamta.fdcompose.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.mamta.fdcompose.R
import com.mamta.fdcompose.ui.theme.FDComposeTheme

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannels()

        setContent {
            NotificationApp()
        }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val postChannel = NotificationChannel(
                "post_channel",
                "Post Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Notifications for new posts" }

            val storyChannel = NotificationChannel(
                "story_channel",
                "Story Notifications",
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Notifications for new stories" }

            val messageChannel = NotificationChannel(
                "message_channel",
                "Message Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Notifications for new messages" }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannels(
                listOf(
                    postChannel,
                    storyChannel,
                    messageChannel
                )
            )
        }
    }
}

@Composable
fun NotificationApp() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { sendNotification(context, "post_channel", "New Post", "Someone uploaded a new post.") }) {
            Text("Send Post Notification")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { sendNotification(context, "story_channel", "New Story", "A new story is available!") }) {
            Text("Send Story Notification")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { sendNotification(context, "message_channel", "New Message", "You received a new message!") }) {
            Text("Send Message Notification")
        }
    }
}



fun sendNotification(context: Context, channelId: String, title: String, message: String) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_cake_24)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.notify((0..1000).random(), builder.build()) // Random ID to show multiple notifications
}



