package github.com.alexescg.auctioneer.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class Model {
    @JsonProperty("_id") var id: String? = null
    @JsonProperty("createdAt") val createdAt: Date? = Date()
    @JsonProperty("updatedAt") val updatedAt: Date? = Date()
}