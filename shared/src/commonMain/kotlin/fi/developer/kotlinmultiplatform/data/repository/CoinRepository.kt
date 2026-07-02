package fi.developer.kotlinmultiplatform.data.repository

import fi.developer.kotlinmultiplatform.data.remote.CoinApiInterface
import fi.developer.kotlinmultiplatform.data.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.data.model.coin.CoinItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepository(
    private val networkApi: CoinApiInterface,
) : CoinRepositoryInterface {

    override fun getCoins(): Flow<List<CoinItem>> = flow {
        val coins = networkApi.getCoins()
        emit(coins)
    }

    override fun getCoinDetails(coinId: String): Flow<CoinDetails> = flow {
        val details = networkApi.getCoinDetails(coinId)
        emit(details)
    }
}