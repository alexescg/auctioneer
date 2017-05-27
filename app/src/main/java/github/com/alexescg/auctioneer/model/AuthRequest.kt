package github.com.alexescg.auctioneer.model

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
class AuthRequest(val email: String, val password: String) {

    val strategy: String = "local"

}