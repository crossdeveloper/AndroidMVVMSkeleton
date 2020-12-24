package com.crossdeveloper.mvvmskeleton.repositories

import com.crossdeveloper.mvvmskeleton.data.model.CountryData
import com.crossdeveloper.mvvmskeleton.data.restapi.HolidaysApi
import com.crossdeveloper.mvvmskeleton.data.restapi.Server
import io.reactivex.Single

class CountryRepository(private val api: HolidaysApi) {
    fun getCountryList(country: String): Single<List<CountryData>> {
        val params = mapOf("key" to Server.apiKey)
        return api.getCountries(params)
                .map { response->
                    response.body()?.countries?.map {  it.toData() }
                }
    }
}