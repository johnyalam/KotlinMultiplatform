package fi.developer.kotlinmultiplatform.presentation.viewmodel.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.developer.kotlinmultiplatform.data.model.coin.Resource
import fi.developer.kotlinmultiplatform.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}