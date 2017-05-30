package github.com.alexescg.auctioneer.model

import java.io.Serializable

/**
 *
 *
 * @author alex
 * @since 5/26/17.
 */
data class User(val email: String,
                val password: String?,
                val avatar: String?,
                val name: String?) : Model(), Serializable {
    override fun toString(): String {
        return "User(id = '$id' email='$email', password=$password, avatar=$avatar, name=$name)"
    }
}