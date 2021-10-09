package adams.sheek.rapcloud.utils.exo

import adams.sheek.rapcloud.App
import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import javax.inject.Inject

class RapPlayer @Inject constructor(private val player: SimpleExoPlayer) {


//    companion object {
//        private const val PLAYBACK_CHANNEL_ID = "playback_channel"
//        private const val PLAYBACK_NOTIFICATION_ID = 199
//        private const val MEDIA_SESSION_TAG = "player_media_podcast"
//    }
//
//    private lateinit var notificationManager: PlayerNotificationManager
//    private lateinit var mediaSession: MediaSessionCompat
//
//    private fun initialiseNotification(context: Context, simpleExoPlayer: SimpleExoPlayer) {
//        this.mediaSession = MediaSessionCompat(context, MEDIA_SESSION_TAG).apply {
//            isActive = true
//        }
//        this.notificationManager = PlayerNotificationManager.Builder(context, PLAYBACK_NOTIFICATION_ID, PLAYBACK_CHANNEL_ID).build()
//
//        this.notificationManager.apply {
//            setMediaSessionToken(mediaSession.sessionToken)
//            setPlayer(simpleExoPlayer)
//            setColorized(true)
//            setUseChronometer(true)
//            setSmallIcon(R.drawable.ic_notif)
//            setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//            setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
//            setPriority(NotificationCompat.PRIORITY_LOW)
//            setControlDispatcher(controlDispatcher)
//        }
//    }

    fun play(path: String){
        player.addMediaItem(MediaItem.fromUri(path))
        player.playWhenReady = true
        player.prepare()
    }
    fun play(resId: Int){
        val rawDataSource = RawResourceDataSource(App.context())
        rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(resId)))
        rawDataSource.uri?.let {
            val dataSourceFactory = DefaultDataSourceFactory(App.context(), Util.getUserAgent(App.context(), App.context().applicationInfo.name))
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(it))
            player.addMediaSource(mediaSource)
            player.deviceVolume = 8
            player.playWhenReady = true
            player.prepare()
        }
    }
    fun play(uri: Uri){
        player.addMediaItem(MediaItem.fromUri(uri))
        player.playWhenReady = true
        player.prepare()
    }

    fun getCurrentPlayer() = player
    fun getCurrentPosition() = player.currentPosition


}