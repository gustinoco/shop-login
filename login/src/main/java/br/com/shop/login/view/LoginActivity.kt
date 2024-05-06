package br.com.shop.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.shop.commons.ShopRoutes
import br.com.shop.login.AnalyticsConstants
import br.com.shop.login.LoginModuleSession
import br.com.shop.login.databinding.LoginActivityMainBinding
import br.com.shop.login.viewmodel.LoginViewEvent
import br.com.shop.login.viewmodel.LoginViewModel
import br.com.shop.login.viewmodel.LoginViewModelFactory
import br.com.shop.login.viewmodel.LoginViewState

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: LoginActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(ShoploginSession.theme)
        binding = LoginActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initObservers()
    }

    fun initObservers() {
        viewModel.viewState.observe(this) {
            when (it) {
                is LoginViewState.Success ->
                    LoginModuleSession.dependencies.shopNavigate?.navigate(this, ShopRoutes.Home.route)
            }
        }

        viewModel.viewEvent.observe(this) {
            when (it) {
                is LoginViewEvent.Error -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                is LoginViewEvent.Loading -> if (it.show) {
                    binding.buttonLogin.isActivated = false
                } else {
                    binding.buttonLogin.isActivated = false
                }
            }
        }
    }

    fun init() {
        LoginModuleSession.dependencies.shopAnalytics?.trackScreen(AnalyticsConstants.TrackScreenHome)

        binding.buttonLogin.setOnClickListener {

            val username = binding.editTextUsername.getText().toString()
            val password = binding.editTextPassword.getText().toString()

            viewModel.login(username, password)
        }
    }
}
