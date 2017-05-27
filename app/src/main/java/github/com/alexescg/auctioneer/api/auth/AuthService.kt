package github.com.alexescg.auctioneer.api.auth

import github.com.alexescg.auctioneer.model.AuthRequest
import github.com.alexescg.auctioneer.model.JsonWebToken
import github.com.alexescg.auctioneer.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


/**
 *
 *
 * @author alex
 * @since 5/26/17.
 */
interface AuthService {
    @POST("/authentication")
    fun auth(@Body auth: AuthRequest): Call<JsonWebToken>
}