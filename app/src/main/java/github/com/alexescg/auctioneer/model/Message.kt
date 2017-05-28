package github.com.alexescg.auctioneer.model

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
class Message(val text: String, val user :User) : Model() {

    override fun toString(): String {
        return "Message(text='$text', user='${user.email}')"
    }
}