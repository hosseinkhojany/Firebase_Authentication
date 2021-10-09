package adams.sheek.rapcloud.di.singletonComponent.data.datasource.remote

import adams.sheek.rapcloud.data.config.retrofit.EndPoints
import adams.sheek.rapcloud.data.datasource.remote.RemoteRapDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Service {

    @Provides
    @Singleton
    fun provideRemoteRapDataSource(service: EndPoints): RemoteRapDataSource {
        return RemoteRapDataSource(service)
    }

}