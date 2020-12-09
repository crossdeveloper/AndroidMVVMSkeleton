package com.crossdeveloper.mvvmskeleton.data.model

data class HolidaysData (
        val holidays: List<HolidayData>
)

data class HolidayData (
        val name: String?,
        val date: String?,
        val country: String?,
        val `public`: Boolean?
)