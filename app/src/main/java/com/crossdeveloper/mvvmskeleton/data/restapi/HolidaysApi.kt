package com.crossdeveloper.mvvmskeleton.data.restapi

import com.crossdeveloper.mvvmskeleton.data.restapi.dto.CountriesDto
import com.crossdeveloper.mvvmskeleton.data.restapi.dto.HolidaysDto
import com.crossdeveloper.mvvmskeleton.data.restapi.retrofit.Json
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HolidaysApi {
    @GET("countries?pretty") @Json
    fun getCountries(@QueryMap params: Map<String, String>): Single<Response<CountriesDto>>

    @GET("holidays?pretty") @Json
    fun getHolidays(@QueryMap params: Map<String, String>): Single<Response<HolidaysDto>>
}