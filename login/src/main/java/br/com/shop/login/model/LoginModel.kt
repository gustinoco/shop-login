package br.com.shop.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    val cpf: String,
    val lastLogin: String
) : Parcelable