package com.crossdeveloper.mvvmskeleton.di

import com.crossdeveloper.mvvmskeleton.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SharedViewModel(get(), get()) }
}