package github.com.alexescg.auctioneer.api.messages

import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.api.OnCreateResponse
import github.com.alexescg.auctioneer.model.Message
import github.com.alexescg.auctioneer.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
interface MessageService {
    @GET("/messages/from/{from}/to/{to}")
    fun getMessages(@Path("from") from: String, @Path("to") to: String): Call<MutableList<Message>>

    @POST("/messages")
    fun sendMessage(@Body message: Message): Call<Message>

}