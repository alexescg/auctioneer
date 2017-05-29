package github.com.alexescg.auctioneer.api.user

import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.model.User
import retrofit2.Call
import retrofit2.http.GET

/**
 *
 *
 * @author alex
 * @since 5/28/17.
 */
interface UserService {
    @GET("/users")
    fun getUsers(): Call<ApiResponse<List<User>>>
}