package adams.sheek.rapcloud.utils.firebase

import adams.sheek.rapcloud.R
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber

object GoogleAuthHelper {
    private const val tag = "GoogleAuthProvider"
    fun provideSignIntent(context: Context): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso).signInIntent
    }

    fun provideAuthCredential(data: Intent?): AuthCredential?{
        return try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            GoogleAuthProvider.getCredential(account.idToken, null)
        } catch (e: ApiException) {
            Timber.w(tag, "Google sign in failed", e)
            null
        }
    }
}