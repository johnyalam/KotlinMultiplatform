package fi.developer.kotlinmultiplatform.data.model.coin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinItem(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("rank")
    val rank: String = "",
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("is_new")
    val isNew: Boolean = false,
) {
}