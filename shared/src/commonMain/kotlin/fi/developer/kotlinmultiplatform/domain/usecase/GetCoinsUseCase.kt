package fi.developer.kotlinmultiplatform.domain.usecase

import fi.developer.kotlinmultiplatform.data.repository.ICoinRepository
import fi.developer.kotlinmultiplatform.model.coin.CoinItem
import fi.developer.kotlinmultiplatform.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException

class GetCoinsUseCase(
    private val coinRepository: ICoinRepository,
) {
    operator fun invoke(): Flow<Resource<List<CoinItem>>> = flow {
        try {
            emit(Resource.Loading())
            coinRepository.getCoins().collect { coins ->
                emit(Resource.Success(coins))
            }
        } catch (error: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Check your internet connection."
                )
            )
        } catch (error: Exception) {
            emit(Resource.Error(error.message ?: "An unexpected error occurred"))
        }
    }
}