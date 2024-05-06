package br.com.shop.login.repository

import br.com.shop.commons.ShopNetwork
import br.com.shop.login.AnalyticsConstants.urlEndpointHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val api: ShopNetwork?) {

    suspend fun postLogin(
        loginRequest: LoginRequest
    ): LoginResponse {
        val response = withContext(Dispatchers.Default) {
            api?.postNetwork(urlEndpointHome, loginRequest, LoginResponse::class) as LoginResponse
        }
        return response
    }
}