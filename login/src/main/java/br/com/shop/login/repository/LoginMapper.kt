package br.com.shop.login.repository

import br.com.shop.login.model.LoginModel

object LoginMapper {

    fun convertResponseToModel(
        response: LoginResponse,
        cpf: String
    ) = LoginModel(
        lastLogin = response.lastLogin,
        cpf = cpf
    )
}
