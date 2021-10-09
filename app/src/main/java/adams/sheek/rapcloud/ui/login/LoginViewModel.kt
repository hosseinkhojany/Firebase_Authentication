package adams.sheek.rapcloud.ui.login

import adams.sheek.rapcloud.App
import adams.sheek.rapcloud.R
import adams.sheek.rapcloud.data.model.User
import adams.sheek.rapcloud.data.repository.LoginRepository
import adams.sheek.rapcloud.utils.SharedConfig
import adams.sheek.rapcloud.utils.enums.LoginType
import adams.sheek.rapcloud.utils.exo.RapPlayer
import adams.sheek.rapcloud.utils.extension.Toaster
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Bindable
import androidx.navigation.Navigation.findNavController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(@ApplicationContext context: Context,
                                         private val loginRepository: LoginRepository,
                                         private val  rapPlayer: RapPlayer) :
    BindingViewModel() {

    init {
        rapPlayer.play(R.raw.login_track)
    }

    private val firebaseAuth: FirebaseAuth by lazy{ Firebase.auth }

    @get:Bindable
    var isLoadingGo: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isLoadingSignWithGoogle: Boolean by bindingProperty(false)
        private set

    fun login(view: View, authCredential: AuthCredential) {
        if (!isLoadingSignWithGoogle && !isLoadingGo) {
            isLoadingSignWithGoogle = true
            firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController(view).navigate(R.id.action_loginFragment_to_rapperListFragment)
                    } else {
                        it.exception?.message?.let { ex ->
                            Toaster.show(
                                if (ex.contains("403") || ex.contains("Forbidden"))
                                    App.context().getString(R.string.google_tahrim) else ex
                            )
                        }
                    }
                    isLoadingSignWithGoogle = false
                }
        }
    }

    fun login(
        email: String, password: String,
        /*0 signUp, 1 signIn*/loginType: LoginType
    ) {
        if (!isLoadingSignWithGoogle && !isLoadingGo) {
            isLoadingGo = true
            val actionCodeSettings = actionCodeSettings {
                url = "https://powerlifter-shahbaz-com.firebaseapp.com/?email=$email"
                handleCodeInApp = true
                setAndroidPackageName("adams.sheek.rapcloud", true, "12" )
            }

            firebaseAuth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        SharedConfig.setUser(User(email = email))
                        Toaster.show(App.context().getString(R.string.authenticateion_link_sent))
                        isLoadingGo = false
                    }
                }
            /* this way just need email and password to signup and signin
            if (loginType == LoginType.SIGNUP) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            validateFirebaseUser(firebaseAuth.currentUser)
                        } else {
                            it.exception?.message?.let { ex ->
                                Toaster.show(
                                    if (ex.contains("403") || ex.contains("Forbidden"))
                                        App.context().getString(R.string.google_tahrim) else ex
                                )
                            }
                        }
                        isLoadingGo = false
                    }
            } else {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            validateFirebaseUser(firebaseAuth.currentUser)
                        } else {
                            it.exception?.message?.let { ex ->
                                Toaster.show(
                                    if (ex.contains("403") || ex.contains("Forbidden"))
                                        App.context().getString(R.string.google_tahrim) else ex
                                )
                            }
                        }
                        isLoadingGo = false
                    }
            }
             */
        }
    }


    private fun validateFirebaseUser(firebaseUser: FirebaseUser?){
        firebaseUser?.let { user ->
            if (!user.isEmailVerified){
                user.delete()
                Toaster.show(App.context().getString(R.string.email_is_not_valid))
            }else{
                Toaster.show(App.context().getString(R.string.success_login))
            }
        }
    }


}