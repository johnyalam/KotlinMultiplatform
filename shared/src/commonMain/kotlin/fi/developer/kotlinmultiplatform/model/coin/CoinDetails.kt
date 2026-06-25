package fi.developer.kotlinmultiplatform.model.coin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetails(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("rank")
    val rank: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("type")
    val type: String,
    @SerialName("description")
    val description: String = "",
    @SerialName("message")
    val message: String = "",
    @SerialName("open_source")
    val openSource: Boolean = false,
    @SerialName("started_at")
    val startedAt: String = "",
    @SerialName("development_status")
    val developmentStatus: String = "",
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean = false,
    @SerialName("proof_type")
    val proofType: String = "",
    @SerialName("org_structure")
    val orgStructure: String = "",
    @SerialName("whitepaper")
    val whitepaper: String = "",
) {
}