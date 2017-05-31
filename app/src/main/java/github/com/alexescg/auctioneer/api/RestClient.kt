package github.com.alexescg.auctioneer.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.Authenticator
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * Rest Client impl for project
 *
 * @author alex
 * @since 5/26/17.
 */
class RestClient {
    companion object {
        private val baseUrl: String = "http://172.20.10.2:3030"
        private var retrofit: Retrofit
        fun <T> createService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass);
        }

        fun addAuthCredentials(authenticator: Authenticator) {
            val client: OkHttpClient = OkHttpClient()
                    .newBuilder()
                    .authenticator(authenticator)
                    .build()

            val kotlinMapper = ObjectMapper().registerModule(KotlinModule())
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(
                            JacksonConverterFactory.create(kotlinMapper))
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create()
                    )
                    .build()
        }

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