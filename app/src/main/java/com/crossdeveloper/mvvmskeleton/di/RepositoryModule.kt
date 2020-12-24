package com.crossdeveloper.mvvmskeleton.di

import com.crossdeveloper.mvvmskeleton.repositories.CountryRepository
import com.crossdeveloper.mvvmskeleton.repositories.HolidayRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HolidayRepository(get()) }
    single { CountryRepository(get()) }
}