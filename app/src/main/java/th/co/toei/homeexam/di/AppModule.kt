package th.co.toei.homeexam.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import th.co.toei.homeexam.viewmodel.MainActivityViewModel
import th.co.toei.homeexam.repository.MainRepositoryImpl
import th.co.toei.homeexam.network.ApiService
import th.co.toei.homeexam.network.EndpointInterface
import th.co.toei.homeexam.usecase.GetPhotosListUseCase

val serviceModule = module {
    single { ApiService() }

    single {
        val apiService: ApiService = get()
        MainRepositoryImpl(apiService.getEndpointInterface(EndpointInterface::class.java))
    }
}

val useCaseModule = module {
    factory { GetPhotosListUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}