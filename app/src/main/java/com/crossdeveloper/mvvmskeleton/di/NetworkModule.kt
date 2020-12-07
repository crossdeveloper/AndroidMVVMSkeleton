package com.crossdeveloper.mvvmskeleton.di

import com.crossdeveloper.mvvmskeleton.BuildConfig
import com.crossdeveloper.mvvmskeleton.data.restapi.Server
import com.crossdeveloper.mvvmskeleton.data.restapi.retrofit.JsonXmlConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val networkModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }

    single {
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor {chain ->
                chain.proceed(chain.request().newBuilder().apply {
                    header("Content-Type", "application/json")
                }.build())
            }
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })

        }.build()
    }


    single {
        SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))
    }

    single { GsonBuilder().create() }

    single {
        GsonConverterFactory.create(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(Server.BASE_URL)
            .addConverterFactory(JsonXmlConverterFactory(get(), get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }
}
