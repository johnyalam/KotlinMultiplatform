package fi.developer.kotlinmultiplatform.data.repository

import fi.developer.kotlinmultiplatform.data.remote.ICoinApi
import fi.developer.kotlinmultiplatform.data.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.data.model.coin.CoinItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepository(
    private val iCoinApi: ICoinApi,
) : ICoinRepository {

    override fun getCoins(): Flow<List<CoinItem>> = flow {
        val coins = iCoinApi.getCoins()
        emit(coins)
    }

    override fun getCoinDetails(coinId: String): Flow<CoinDetails> = flow {
        val details = iCoinApi.getCoinDetails(coinId)
        emit(details)
    }
}