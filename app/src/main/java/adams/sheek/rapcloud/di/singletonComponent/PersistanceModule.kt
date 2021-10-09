package adams.sheek.rapcloud.di.singletonComponent

import adams.sheek.rapcloud.data.repository.RapRepository
import adams.sheek.rapcloud.utils.SharedConfig
import adams.sheek.rapcloud.utils.exo.RapPlayer
import adams.sheek.rapcloud.utils.extension.Toaster
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.C.CONTENT_TYPE_MUSIC
import com.google.android.exoplayer2.C.USAGE_MEDIA
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistanceModule {

    @Provides
    @Singleton
    fun providePlayer(@ApplicationContext context: Context): SimpleExoPlayer{
        val audioAttributes = AudioAttributes.Builder().run {
            setUsage(USAGE_MEDIA)
            setContentType(CONTENT_TYPE_MUSIC)
            build()
        }
        return SimpleExoPlayer.Builder(context, DefaultRenderersFactory(context))
            .setAudioAttributes(audioAttributes, true)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRapPlayer(simpleExoPlayer: SimpleExoPlayer) = RapPlayer(simpleExoPlayer)


}