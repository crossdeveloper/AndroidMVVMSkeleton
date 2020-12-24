package com.crossdeveloper.mvvmskeleton.repositories

import com.crossdeveloper.mvvmskeleton.data.model.HolidayData
import com.crossdeveloper.mvvmskeleton.data.restapi.HolidaysApi
import com.crossdeveloper.mvvmskeleton.data.restapi.Server
import io.reactivex.Single

class HolidayRepository (private val api: HolidaysApi) {
    fun getHolidayList(country: String): Single<List<HolidayData>> {
        val params = mapOf("key" to Server.apiKey, "country" to country, "year" to "2020")
        return api.getHolidays(params)
                .map { response->
                    response.body()?.holidays?.map {  it.toData() }
                }
    }
}
