package br.com.shop.login.repository

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("cpf")
    val cpf: String? = null,
    @SerializedName("password")
    val password: String? = null)