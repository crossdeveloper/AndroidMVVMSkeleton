package com.crossdeveloper.mvvmskeleton.data.restapi.dto

data class CountriesDto (
        val countries: List<CountryDto>
)
data class CountryDto (
        val code: String?,
        val name: String?,
        val flag: String?
)