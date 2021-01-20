package com.crossdeveloper.mvvmskeleton.ui.holiday

import androidx.lifecycle.MutableLiveData
import com.crossdeveloper.mvvmskeleton.data.base.BaseViewModel
import com.crossdeveloper.mvvmskeleton.data.model.HolidayData
import com.crossdeveloper.mvvmskeleton.extensions.with
import com.crossdeveloper.mvvmskeleton.repositories.HolidayRepository

class HolidayListViewModel(private val holidayRepository: HolidayRepository): BaseViewModel() {

    val holidays = MutableLiveData<List<HolidayData>>()

    fun loadHolidays(country: String) {
        addToDisposable(holidayRepository.getHolidayList(country).with()
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