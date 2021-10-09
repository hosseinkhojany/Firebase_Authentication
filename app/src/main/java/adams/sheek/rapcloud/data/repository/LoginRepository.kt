package adams.sheek.rapcloud.data.repository

import adams.sheek.rapcloud.data.datasource.local.LocalRapDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val localDs: LocalRapDataSource,
    private val coroutineDispatcher: CoroutineDispatcher) : Repository{




//    fun validateUser(authCredential: AuthCredential,
//                    onStart: () -> Unit,
//                    onComplete: () -> Unit,
//                    onError: (String) -> Unit) = channelFlow {
//        firebaseAuth.signInWithCredential(authCredential)
//            .addOnCompleteListener {
//                if (it.isSuccessful){
//                    launch { send(firebaseAuth.currentUser); onComplete()  }
//                }else{
//                    it.exception?.message?.let { ex ->
//                        val err = if (ex.contains("403") || ex.contains("Forbidden")){
//                            App.context().getString(R.string.google_tahrim)
//                        }else{
//                            ex
//                        }
//                        onError(err)
//                    }
//                }
//        }
//    }.onStart { onStart() }.flowOn(coroutineDispatcher)
}