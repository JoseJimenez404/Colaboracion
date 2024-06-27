package com.example.myapplication
import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.R

class MainActivity : ComponentActivity() {

    companion object {
        const val CHANNEL_ID = "my_channel_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)


        button1.setOnClickListener { sendNotification("Jose Jimenez", "The Mikeee") }
        button2.setOnClickListener { sendNotification("Sergio Amaury", "MauMau heroes de puro corazon") }
        
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Channel for my app notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    var id = 0;
    @SuppressLint("MissingPermission")
    private fun sendNotification(title: String, description: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background) // Asegúrate de tener un ícono en tu carpeta drawable
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        id +=1;
        with(NotificationManagerCompat.from(this)) {

            notify((title.hashCode() + description.hashCode()) % Int.MAX_VALUE, builder.build())
        }
    }
}
