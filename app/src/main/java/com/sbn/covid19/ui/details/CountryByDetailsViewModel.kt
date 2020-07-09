package com.sbn.covid19.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.PieEntry
import com.sbn.covid19.shared.domain.covid.CountryDetailsUseCase
import com.sbn.covid19.shared.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CountryByDetailsViewModel @ViewModelInject constructor(private val useCase: CountryDetailsUseCase) :
    ViewModel() {

    val cases = MutableLiveData<String>()
    val deaths = MutableLiveData<String>()
    val recovered = MutableLiveData<String>()
    val list = MutableLiveData<List<PieEntry>>()
    val name = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun country(name: String) {
        this.name.postValue(name)
        viewModelScope.launch(Dispatchers.IO){
            useCase(name).collect{
                if( it is Result.Success) {
                    val totalCase = it.data.cases
                    val totalDeaths = it.data.deaths
                    val totalRecovered = it.data.recovered
                    val activeCase = it.data.active
                    cases.postValue(totalCase.toString())
                    deaths.postValue(totalDeaths.toString())
                    recovered.postValue(totalRecovered.toString())
                    list.postValue(listOf(PieEntry(totalCase.toFloat(),"Total case"),
                        PieEntry(activeCase.toFloat(),"Active case"),
                        PieEntry(totalDeaths.toFloat(),"Total deaths"),
                        PieEntry(totalRecovered.toFloat(),"Total recoverd")
                    ))
                }
            }
        }
    }
}