package fi.developer.kotlinmultiplatform.di

import fi.developer.kotlinmultiplatform.data.remote.CoinApi
import fi.developer.kotlinmultiplatform.data.remote.ICoinApi
import fi.developer.kotlinmultiplatform.data.remote.createHttpClient
import fi.developer.kotlinmultiplatform.data.repository.CoinRepository
import fi.developer.kotlinmultiplatform.data.repository.ICoinRepository
import fi.developer.kotlinmultiplatform.domain.usecase.GetCoinsUseCase
import fi.developer.kotlinmultiplatform.presentation.viewmodel.coin.CoinViewModel
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single<ICoinApi> { CoinApi(get()) }
}

val repositoryModule = module {
    single<ICoinRepository> { CoinRepository(get()) }
}

val useCaseModule = module {
    factory { GetCoinsUseCase(get()) }
}

val viewModelModule = module {
    factory { CoinViewModel(get()) }
}

val appModules = listOf(networkModule, repositoryModule, useCaseModule, viewModelModule)