package org.example.kmpmercadolibre.di


import io.ktor.client.HttpClient
import org.example.kmpmercadolibre.data.remote.EccomerceApi
import org.example.kmpmercadolibre.data.repository.ProductRepositoryImpl
import org.example.kmpmercadolibre.domain.repository.ProductRepository
import org.example.kmpmercadolibre.domain.usecases.ProductsUseCase
import org.example.kmpmercadolibre.ui.screen.homeScreen.ProductViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {

    single { HttpClient() }
    single { EccomerceApi() }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}

private val domainModule = module {
    single { ProductsUseCase(get()) }
}


private val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
}


var sharedModules = listOf(domainModule, dataModule, viewModelModule)


