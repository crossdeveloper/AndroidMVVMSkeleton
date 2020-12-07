package com.crossdeveloper.mvvmskeleton.di

val appModule = networkModule
    .plus(repositoryModule)
    .plus(apiModule)

