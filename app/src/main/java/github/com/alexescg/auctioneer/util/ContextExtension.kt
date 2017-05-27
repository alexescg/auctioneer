package github.com.alexescg.auctioneer.util

import android.content.Context
import android.widget.Toast

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
class ContextExtension {
    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}