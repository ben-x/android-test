package ng.riby.androidtest.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import ng.riby.androidtest.R
import ng.riby.androidtest.others.Constants.ACTION_PAUSE_SERVICE
import ng.riby.androidtest.others.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import ng.riby.androidtest.others.Constants.ACTION_START_OR_RESUME_SERVICE
import ng.riby.androidtest.others.Constants.ACTION_STOP_SERVICE
import ng.riby.androidtest.others.Constants.NOTIFICATION_CHANNEL_ID
import ng.riby.androidtest.others.Constants.NOTIFICATION_CHANNEL_NAME
import ng.riby.androidtest.others.Constants.NOTIFICATION_ID
import ng.riby.androidtest.ui.MainActivity
import timber.log.Timber

class TrackingService : LifecycleService() {

    //boolean to tell if its the first run
    var isFirstRun = true

    //called whenever we send a command to our service
    //action to start or resume, stop, pause
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action){
                ACTION_START_OR_RESUME_SERVICE ->{
                    if(isFirstRun){
                        startForegroundService()
                        isFirstRun = false
                    }else{
                        Timber.d("Resuming service")
                    }
                }
                ACTION_PAUSE_SERVICE ->{
                    Timber.d("paused service")
                }
                ACTION_STOP_SERVICE ->{
                    Timber.d("stopped service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(false)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
                .setContentTitle("Tracking App")
                .setContentText("00:00:00")
                .setContentIntent(getMainActivityPendingIntent())

        //start foreground service
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }



    //returns pending intent
    private fun getMainActivityPendingIntent()= PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java).also {
                it.action = ACTION_SHOW_TRACKING_FRAGMENT
            },
            FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}