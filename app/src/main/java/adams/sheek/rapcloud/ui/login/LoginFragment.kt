package adams.sheek.rapcloud.ui.login

import adams.sheek.rapcloud.R
import adams.sheek.rapcloud.databinding.FragmentLoginBinding
import adams.sheek.rapcloud.utils.enums.LoginType
import adams.sheek.rapcloud.utils.firebase.GoogleAuthHelper
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() , View.OnClickListener{

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private var registered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleSignInLauncher = registerForActivityResult(StartActivityForResult()){ result ->
            GoogleAuthHelper.provideAuthCredential(result.data)?.let {
                viewModel.login(view, it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        setViewOnClickListeners()
        binding.vm = viewModel
        return binding.root
    }


    override fun onStop() {
        super.onStop()
    }

    private fun setViewOnClickListeners(){
        binding.login.setOnClickListener(this)
        binding.signInWithGoogle.setOnClickListener(this)
        binding.beforeRegistered.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view){
            binding.login -> {
                viewModel.login(binding.email.text.toString(), binding.password.text.toString(),
                    if (registered) LoginType.SIGNIN else LoginType.SIGNUP)
            }
            binding.signInWithGoogle -> {
                googleSignInLauncher.launch(GoogleAuthHelper.provideSignIntent(requireContext()))
            }
            binding.beforeRegistered -> {
                if (!registered){
                    registered = true
                    binding.beforeRegistered.text = getString(R.string.signup_label)
                    binding.repeatPassword.visibility = View.INVISIBLE
                }else{
                    registered = false
                    binding.beforeRegistered.text = getString(R.string.signin_label)
                    binding.repeatPassword.visibility = View.VISIBLE
                }
            }
        }
    }

}