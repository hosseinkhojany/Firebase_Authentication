package adams.sheek.rapcloud.ui.main

import adams.sheek.rapcloud.App
import adams.sheek.rapcloud.R
import adams.sheek.rapcloud.data.model.User
import adams.sheek.rapcloud.utils.SharedConfig
import adams.sheek.rapcloud.utils.extension.Toaster
import androidx.navigation.Navigation.findNavController
import android.content.Intent
import android.view.View
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.bindables.BindingViewModel


class MainViewModel(): BindingViewModel(){

    fun validateAuthenticateIntent(view: View, intent: Intent){

        val auth = Firebase.auth
        val emailLink = intent.data.toString()

        if (auth.isSignInWithEmailLink(emailLink)) {
            val email = SharedConfig.getUser().email
            auth.signInWithEmailLink(email, emailLink)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toaster.show(App.context().getString(R.string.success_login))
                        auth.currentUser?.let {
                            SharedConfig.setUser(
                                User(displayName = it.displayName ?: "",
                                    email = it.email ?: "",
                                    phoneNumber = it.phoneNumber ?: "",
                                    uId = it.uid
                                )
                            )
                            findNavController(view).navigate(R.id.action_loginFragment_to_rapperListFragment)
                        }

                    }
                }
        }

    }


}