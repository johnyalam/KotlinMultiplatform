package fi.developer.kotlinmultiplatform.data.remote

import fi.developer.kotlinmultiplatform.model.coin.CoinDetails
import fi.developer.kotlinmultiplatform.model.coin.CoinItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get



class NetworkApi(
    private val httpClient: HttpClient,
) : INetworkApi {
    private val baseUrl = "https://api.coinpaprika.com/v1"

    override suspend fun getCoins(): List<CoinItem> {
        return httpClient.get("$baseUrl/coins").body()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetails {
        return httpClient.get("$baseUrl/coins/$coinId").body()
    }
}