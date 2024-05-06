package br.com.shop.login.repository

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("message")
    var lastLogin: String = ""
)