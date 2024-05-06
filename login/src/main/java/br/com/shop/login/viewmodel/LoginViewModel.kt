package br.com.shop.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.shop.login.LoginModuleSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import br.com.shop.login.usecase.LoginUseCase
import br.com.shop.login.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val state: MutableLiveData<LoginViewState> = MutableLiveData()
    private val event: MutableLiveData<LoginViewEvent> = MutableLiveData()
    val viewState: LiveData<LoginViewState> = state
    val viewEvent: LiveData<LoginViewEvent> = event

    private var loginUseCase: LoginUseCase

    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

    init {
        loginUseCase = LoginUseCase(repository)
    }

    fun login(cpf: String, password: String) {

        CoroutineScope(coroutineContext).launch {

            if (cpf.isEmpty()) {
                event.value = LoginViewEvent.Error("CPF é necessário")
                return@launch
            }
            if (cpf.length != 11) {
                event.value = LoginViewEvent.Error("CPF inválido")
                return@launch
            }
            if (password.isEmpty()) {
                event.value = LoginViewEvent.Error("Senha é necessário")
                return@launch
            }


            try {

                event.value = LoginViewEvent.Loading(true)
                val response = loginUseCase.login(cpf, password)
                event.value = LoginViewEvent.Loading(false)

                LoginModuleSession.dependencies.shopCache?.putCache("cart_$cpf", String)

                state.value = LoginViewState.Success(response)
            } catch (e: Exception) {
                print(e.message)
                event.value = LoginViewEvent.Loading(false)
                event.value = LoginViewEvent.Error("Erro ao fazer login")
            }
        }
    }
}
