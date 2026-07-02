package fi.developer.kotlinmultiplatform.presentation.viewmodel.coin

import fi.developer.kotlinmultiplatform.data.model.coin.CoinItem

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinItem> = emptyList(),
    val error: String = ""
)
