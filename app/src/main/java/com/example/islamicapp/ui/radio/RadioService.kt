package com.example.islamicapp.ui.radio

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islamicapp.R
import com.example.islamicapp.utils.ACTION
import com.example.islamicapp.utils.NAME
import com.example.islamicapp.utils.NOTIFICATION_ID
import com.example.islamicapp.utils.PAUSE
import com.example.islamicapp.utils.PLAY
import com.example.islamicapp.utils.RADIO_CHANNEL_ID
import com.example.islamicapp.utils.STOP
import com.example.islamicapp.utils.URL

class RadioService : Service() {

    val mediaPlayer = MediaPlayer()
    var isPrepared = false
    private var name = ""

    override fun onBind(p0: Intent?): IBinder? {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        fun getService(): RadioService {
            return this@RadioService
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val urlToPlay = intent?.getStringExtra(URL)
        val name = intent?.getStringExtra(NAME)

        if (urlToPlay != null && name != null) {
            initMediaPlayer(urlToPlay, name)
        }

        val action = intent?.getStringExtra(ACTION)
        when (action) {
            PLAY, PAUSE -> playPauseMediaPlayer()
            STOP -> stopMediaPlayer()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    fun playPauseMediaPlayer() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()
        else
            mediaPlayer.start()

        updateNotification()

    }


    fun initMediaPlayer(urlToPlay: String, name: String) {
        this.name = name
        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(urlToPlay)
            prepareAsync()
            setOnPreparedListener {
                isPrepared = true
                startMediaPlayer()
                createNotification()
            }
        }
    }

    private fun createNotification() {
        val remoteView = RemoteViews(packageName, R.layout.notification_view)
        remoteView.setTextViewText(R.id.title, getString(R.string.app_name))
        remoteView.setTextViewText(R.id.description, name)
        remoteView.setImageViewResource(
            R.id.play,
            if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
        remoteView.setOnClickPendingIntent(R.id.play, getPlayPendingIntent())

        val notification = NotificationCompat.Builder(this, RADIO_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_quran)
            .setCustomContentView(remoteView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()



        startForeground(NOTIFICATION_ID, notification)
    }

    private fun updateNotification() {
        val remoteView = RemoteViews(packageName, R.layout.notification_view)
        remoteView.setTextViewText(R.id.title, getString(R.string.app_name))
        remoteView.setTextViewText(R.id.description, name)
        remoteView.setImageViewResource(
            R.id.play, if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
        remoteView.setOnClickPendingIntent(R.id.play, getPlayPendingIntent())

        var notification = NotificationCompat.Builder(this, RADIO_CHANNEL_ID)
            .setSmallIcon(R.drawable.splash_image)
            .setCustomContentView(remoteView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        onPlayClick?.onClick(mediaPlayer.isPlaying)
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(NOTIFICATION_ID, notification)

    }

    private fun getPlayPendingIntent(): PendingIntent? {
        val intent = Intent(this, RadioService::class.java)
        if (mediaPlayer.isPlaying)
            intent.putExtra(ACTION, PAUSE)
        else
            intent.putExtra(ACTION, PLAY)

        //request code 12
        return PendingIntent.getService(this, 12, intent, PendingIntent.FLAG_IMMUTABLE)
    }


    // to communicate with fragment

    fun getIsPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun startMediaPlayer() {
        if (isPrepared)
            mediaPlayer.start()
    }

    var onPlayClick: OnPlayClick? = null

    fun interface OnPlayClick {
        fun onClick(isPlayed: Boolean)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopMediaPlayer()

    }

}



