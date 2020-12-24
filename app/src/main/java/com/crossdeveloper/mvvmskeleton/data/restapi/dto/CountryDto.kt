package com.crossdeveloper.mvvmskeleton.data.restapi.dto

import com.crossdeveloper.mvvmskeleton.data.model.CountryData

data class CountriesDto (
        val status: Int,
        val countries: List<CountryDto>
)
data class CountryDto (
        val code: String?,
        val name: String?,
        val flag: String?
) {
    fun toData(): CountryData {
        return CountryData(code = code, name = name, flag = flag)
    }
}