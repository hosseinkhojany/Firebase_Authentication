package adams.sheek.rapcloud.ui.main

import adams.sheek.rapcloud.R
import adams.sheek.rapcloud.databinding.ActivityMainBinding
import adams.sheek.rapcloud.utils.extension.Toaster
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toaster.register(this)

        intent?.let {
            viewModel.validateAuthenticateIntent(it)
        }

    }



}