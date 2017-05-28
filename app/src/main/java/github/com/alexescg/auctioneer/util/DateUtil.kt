package github.com.alexescg.auctioneer.util

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @author alex
 * @since 5/28/17.
 */
class DateUtil {
    companion object {
        fun formatDateToTime(date: Date): String {
            return SimpleDateFormat("HH:mm", Locale.US).format(date)
        }

    }
}