package github.com.alexescg.auctioneer.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonUnwrapped

/**
 *
 *
 * @author alex
 * @since 5/30/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class OnCreateResponse<T>(@JsonUnwrapped val model: T) {
}