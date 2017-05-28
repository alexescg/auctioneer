package github.com.alexescg.auctioneer.api

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
data class ApiResponse<T>(val total: Int, val limit: Int, val skip: Int, val data: T)