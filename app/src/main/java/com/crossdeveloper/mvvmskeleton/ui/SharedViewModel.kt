package com.crossdeveloper.mvvmskeleton.ui

import androidx.lifecycle.MutableLiveData
import com.crossdeveloper.mvvmskeleton.data.base.BaseViewModel
import com.crossdeveloper.mvvmskeleton.data.model.CountryData
import com.crossdeveloper.mvvmskeleton.data.model.HolidayData
import com.crossdeveloper.mvvmskeleton.extensions.with
import com.crossdeveloper.mvvmskeleton.repositories.CountryRepository
import com.crossdeveloper.mvvmskeleton.repositories.HolidayRepository

class SharedViewModel(
    private val countryRepo: CountryRepository,
    private val holidayRepo: HolidayRepository
): BaseViewModel() {

    val countries = MutableLiveData<List<CountryData>>()
    val holidays = MutableLiveData<List<HolidayData>>()

    fun loadCountries() {
        addToDisposable(countryRepo.getCountryList().with()
            .doOnSubscribe { isLoading.value = true }
            .doOnSuccess { isLoading.value = false }
            .doOnError { isLoading.value = false}
            .subscribe({  countryList ->
                isLoading.postValue(false)
                if (!countryList.isNullOrEmpty()) {
                    countries.postValue(countryList)
                }
                else {
                    errorMessage.value = "Getting events failed"
                }
            }, {
                // handle errors
                errorMessage.value = it.localizedMessage
                isLoading.postValue(false)
            }))
    }

    fun loadHolidays(country: String) {
        addToDisposable(holidayRepo.getHolidayList(country).with()
            .doOnSubscribe { isLoading.value = true }
            .doOnSuccess { isLoading.value = false }
            .doOnError { isLoading.value = false}
            .subscribe({  holidayList ->
                isLoading.postValue(false)
                if (!holidayList.isNullOrEmpty()) {
                    holidays.postValue(holidayList)
                }
                else {
                    errorMessage.value = "Getting events failed"
                }
            }, {
                // handle errors
                errorMessage.value = it.localizedMessage
                isLoading.postValue(false)
            }))
    }
}