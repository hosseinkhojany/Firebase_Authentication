package adams.sheek.rapcloud.di.singletonComponent.data.datasource.local

import adams.sheek.rapcloud.data.config.room.AppDatabase
import adams.sheek.rapcloud.data.datasource.local.LocalRapDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Dao {

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): LocalRapDataSource {
        return appDatabase.pokemonDao()
    }

}