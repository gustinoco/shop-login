package br.com.shop.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.shop.login.LoginModuleSession
import br.com.shop.login.repository.LoginRepository

class LoginViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        LoginViewModel(
            LoginRepository(LoginModuleSession.dependencies.shopNetwork)
        ) as T
}
