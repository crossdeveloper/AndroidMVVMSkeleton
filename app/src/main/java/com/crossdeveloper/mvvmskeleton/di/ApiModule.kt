package com.crossdeveloper.mvvmskeleton.di

import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    //single(createdAtStart = false) { get<Retrofit>().create(UserApi::class.java) }
}