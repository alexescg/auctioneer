package github.com.alexescg.auctioneer.api.auth

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
class JwtAuthenticator(val authToken: String) : Authenticator {
    override fun authenticate(route: Route?, response: Response?): Request? {
        return response!!.request().newBuilder()
                .header("Authorization", authToken).build()
    }

}