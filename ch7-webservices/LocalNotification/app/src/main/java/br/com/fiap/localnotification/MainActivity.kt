package br.com.fiap.localnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
    }

    override fun onDestroy() {

        super.onDestroy()
        finish()
    }

    fun createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("notification", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    fun generateNotificationJApp(view: View?) {

        createNotificationChannel()

        val intent = Intent(this, ResponseScreenActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val p: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var messages = arrayOf("Group Meeting",
            "Math teste",
            "Work presentation")

        val builder = NotificationCompat.Builder(this, "notification")
            .setContentTitle(messages[(Math.random()*messages.size).toInt()])
            .setSmallIcon(R.drawable.pointer)
            .setLargeIcon(Library.decoder())
            .setContentIntent(p)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val style = NotificationCompat.InboxStyle()

        val units = arrayOf(
            arrayOf("FIAP","Campus Vila Olimpia","Rua Olimpíadas, 186","CEP: 04551-000"),
            arrayOf("FIAP","Campus Paulista","Av. Paulista, 1106","CEP: 01311-000"),
            arrayOf("FIAP","Campus Vila Mariana","Av. Lins de Vasconcelos, 1264","CEP: 01538-001")
        )

        var random = (Math.random()*units.size).toInt()
        var unity = units[random]
        for (detail in unity) {
            style.addLine(detail)
        }
        builder.setStyle(style)

        val notif = builder.build()
        notif.flags = Notification.FLAG_AUTO_CANCEL

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(R.drawable.ic_launcher_background, notif)
        }

        try {
            val som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringing = RingtoneManager.getRingtone(this, som)
            ringing.play()
        } catch (e: Exception) {
            Log.i("Error", e.message.toString())
        }
    }
}