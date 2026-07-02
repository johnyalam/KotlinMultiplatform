package fi.developer.kotlinmultiplatform.data.repository

import fi.developer.kotlinmultiplatform.data.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.data.model.coin.CoinItem
import kotlinx.coroutines.flow.Flow

interface CoinRepositoryInterface {
    fun getCoins(): Flow<List<CoinItem>>
    fun getCoinDetails(coinId: String): Flow<CoinDetails>
}