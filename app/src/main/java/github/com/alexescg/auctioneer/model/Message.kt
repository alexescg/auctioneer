package github.com.alexescg.auctioneer.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonUnwrapped

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Message(val text: String, val user: User? = null, val to: User) : Model() {
    override fun toString(): String {
        return "Message(text='$text', user=$user, to=$to)"
    }
}