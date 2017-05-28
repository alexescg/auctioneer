package github.com.alexescg.auctioneer.model

/**
 *
 *
 * @author alex
 * @since 5/26/17.
 */
data class User(val email: String, val password: String?, val avatar: String) : Model()