package com.crossdeveloper.mvvmskeleton.di

import com.crossdeveloper.mvvmskeleton.ui.country.CountryListViewModel
import com.crossdeveloper.mvvmskeleton.ui.holiday.HolidayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryListViewModel(get()) }
    viewModel { HolidayListViewModel(get()) }
}