package br.com.shop.login.viewmodel

sealed class LoginViewEvent {

    data class Loading(val show: Boolean) : LoginViewEvent()

    data class Error(val message: String) : LoginViewEvent()
}