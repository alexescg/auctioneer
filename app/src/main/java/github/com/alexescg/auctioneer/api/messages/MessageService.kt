package github.com.alexescg.auctioneer.api.messages

import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.model.Message
import retrofit2.Call
import retrofit2.http.GET

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
interface MessageService {
    @GET("/messages")
    fun getMessages(): Call<ApiResponse<List<Message>>>
}