package adams.sheek.rapcloud.di.singletonComponent

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

  @Provides
  @Singleton
  fun provideIODispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
  }
}
