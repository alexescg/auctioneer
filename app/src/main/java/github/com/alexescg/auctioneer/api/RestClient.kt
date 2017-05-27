package github.com.alexescg.auctioneer.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Rest Client impl for project
 *
 * @author alex
 * @since 5/26/17.
 */
class RestClient {
    companion object {
        private val baseUrl: String = "http://192.168.1.87:3030"
        private var retrofit: Retrofit

        init {
            val kotlinMapper = ObjectMapper().registerModule(KotlinModule())
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(
                            JacksonConverterFactory.create(kotlinMapper))
                    .build()
        }
    }
}