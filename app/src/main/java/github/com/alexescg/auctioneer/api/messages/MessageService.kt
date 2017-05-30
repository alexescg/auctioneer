package github.com.alexescg.auctioneer.api.messages

import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.api.OnCreateResponse
import github.com.alexescg.auctioneer.model.Message
import github.com.alexescg.auctioneer.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
interface MessageService {
    @GET("/messages")
    fun getMessages(): Call<ApiResponse<MutableList<Message>>>

    @POST("/messages")
    fun sendMessage(@Body message: Message): Call<Message>
}