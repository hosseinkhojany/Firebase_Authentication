package adams.sheek.rapcloud.ui.splash

import adams.sheek.rapcloud.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import adams.sheek.rapcloud.databinding.FragmentSplashScreenBinding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater)
        Glide.with(this).load(R.raw.logo).into(binding.logo)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2600)
            findNavController().navigate(R.id.action_splashScreen_to_loginFragment)
        }
        return binding.root
    }
}