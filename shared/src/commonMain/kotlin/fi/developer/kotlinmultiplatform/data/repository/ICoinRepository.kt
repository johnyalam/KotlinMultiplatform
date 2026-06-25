package fi.developer.kotlinmultiplatform.data.repository

import fi.developer.kotlinmultiplatform.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.model.coin.CoinItem
import kotlinx.coroutines.flow.Flow

interface ICoinRepository {
    fun getCoins(): Flow<List<CoinItem>>
    fun getCoinDetails(coinId: String): Flow<CoinDetails>
}