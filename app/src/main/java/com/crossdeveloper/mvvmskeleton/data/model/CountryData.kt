package com.crossdeveloper.mvvmskeleton.data.model

data class CountriesData (
        val countries: List<CountryData>
)
data class CountryData (
        val code: String?,
        val name: String?,
        val flag: String?
)