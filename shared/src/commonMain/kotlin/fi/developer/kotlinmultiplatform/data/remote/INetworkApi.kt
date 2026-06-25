package fi.developer.kotlinmultiplatform.data.remote

import fi.developer.kotlinmultiplatform.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.model.coin.CoinItem

interface INetworkApi {
    suspend fun getCoins(): List<CoinItem>
    suspend fun getCoinDetails(coinId: String): CoinDetails
}