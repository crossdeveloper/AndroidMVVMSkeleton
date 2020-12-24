package com.crossdeveloper.mvvmskeleton.di

import com.crossdeveloper.mvvmskeleton.data.restapi.HolidaysApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(HolidaysApi::class.java) }
}