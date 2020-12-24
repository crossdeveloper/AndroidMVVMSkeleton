package com.crossdeveloper.mvvmskeleton.data.restapi.dto

import com.crossdeveloper.mvvmskeleton.data.model.HolidayData
import com.crossdeveloper.mvvmskeleton.data.model.HolidaysData

data class HolidaysDto (
        val status: Int,
        val holidays: List<HolidayDto>
) {
    fun toData(): HolidaysData {
        return HolidaysData(
                holidays = holidays.map { it.toData() }
        )
    }
}

data class HolidayDto (
        val name: String,
        val date: String?,
        val country: String?,
        val `public`: Boolean?
) {
    fun toData(): HolidayData {
        return HolidayData(name = name, date = date, country = country, public = public)
    }
}