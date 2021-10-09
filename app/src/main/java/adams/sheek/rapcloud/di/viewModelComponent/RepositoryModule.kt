package adams.sheek.rapcloud.di.viewModelComponent

import adams.sheek.rapcloud.data.datasource.local.LocalRapDataSource
import adams.sheek.rapcloud.data.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(coroutineDispatcher: CoroutineDispatcher, localRapDs: LocalRapDataSource)
    = LoginRepository(coroutineDispatcher = coroutineDispatcher, localDs = localRapDs)


}