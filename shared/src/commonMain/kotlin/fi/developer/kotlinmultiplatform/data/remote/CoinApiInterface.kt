package fi.developer.kotlinmultiplatform.data.remote

import fi.developer.kotlinmultiplatform.data.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.data.model.coin.CoinItem

interface CoinApiInterface {
    suspend fun getCoins(): List<CoinItem>
    suspend fun getCoinDetails(coinId: String): CoinDetails
}