package adams.sheek.rapcloud.di.singletonComponent.data.config.room

import adams.sheek.rapcloud.data.config.room.AppDatabase
import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Config {


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "rapcloud.db")
            .fallbackToDestructiveMigration()
//            .addTypeConverter(typeResponseConverter)
            .build()
    }

}