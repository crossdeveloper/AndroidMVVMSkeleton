package com.crossdeveloper.mvvmskeleton.ui.country

import androidx.lifecycle.MutableLiveData
import com.crossdeveloper.mvvmskeleton.data.base.BaseViewModel
import com.crossdeveloper.mvvmskeleton.data.model.CountryData
import com.crossdeveloper.mvvmskeleton.extensions.with
import com.crossdeveloper.mvvmskeleton.repositories.CountryRepository

class CountryListViewModel(private val countryRepository: CountryRepository): BaseViewModel() {
    val countries = MutableLiveData<List<CountryData>>()

    fun loadCountries() {
        addToDisposable(countryRepository.getCountryList().with()
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
}