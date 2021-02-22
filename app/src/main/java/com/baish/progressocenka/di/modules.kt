package com.baish.progressocenka.di

import com.baish.progressocenka.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModelModule: Module = module {
    viewModel { MainViewModel() }

}

val dbModule: Module = module {

}

val repositoryModule: Module = module {

}

val apiModule: Module = module {

}

val appModules =
    listOf(viewModelModule, apiModule, repositoryModule, dbModule)