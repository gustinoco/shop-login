package br.com.shop.login.viewmodel

import br.com.shop.login.model.LoginModel

sealed class LoginViewState {

    data class Success(val loginModel: LoginModel?) : LoginViewState()
}