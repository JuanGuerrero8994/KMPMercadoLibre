package org.example.kmpmercadolibre.di


import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.HttpClient
import org.example.kmpmercadolibre.data.remote.MercadoLibreApi
import org.example.kmpmercadolibre.data.repository.SearchRepositoryImpl
import org.example.kmpmercadolibre.domain.repository.SearchRepository
import org.example.kmpmercadolibre.domain.usecases.SearchUseCase
import org.example.kmpmercadolibre.ui.screen.homeScreen.SearchViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {

    single { HttpClient() }
    single { MercadoLibreApi() }
    single <SearchRepository>{ SearchRepositoryImpl(get()) }
}

private val domainModule = module {
    single { SearchUseCase(get()) }
}


private val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}


var sharedModules = listOf(domainModule, dataModule, viewModelModule)


