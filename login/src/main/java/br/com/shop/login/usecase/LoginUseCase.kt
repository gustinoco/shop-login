package br.com.shop.login.usecase

import br.com.shop.login.model.LoginModel
import br.com.shop.login.repository.LoginMapper
import br.com.shop.login.repository.LoginRepository
import br.com.shop.login.repository.LoginRequest

class LoginUseCase(private val repository: LoginRepository) {

    suspend fun login(cpf: String, password: String): LoginModel {
        val response = repository.postLogin(LoginRequest(cpf, password))
        return LoginMapper.convertResponseToModel(response, cpf)
    }
}