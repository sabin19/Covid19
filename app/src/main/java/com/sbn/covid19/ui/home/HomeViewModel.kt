package com.sbn.covid19.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.PieEntry
import com.sbn.covid19.shared.domain.covid.CovidUseCase
import com.sbn.covid19.shared.result.Event
import com.sbn.covid19.shared.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(useCase: CovidUseCase) : ViewModel() {

    val cases = MutableLiveData<String>()
    val deaths = MutableLiveData<String>()
    val recovered = MutableLiveData<String>()
    val list = MutableLiveData<List<PieEntry>>()

    private val _clickByCase = MutableLiveData<Event<Unit>>()
    val clickByCase: LiveData<Event<Unit>> get() = _clickByCase

    private val _clickByDeaths = MutableLiveData<Event<Unit>>()
    val clickByDeaths: LiveData<Event<Unit>> get() = _clickByDeaths

    private val _clickByRecovered = MutableLiveData<Event<Unit>>()
    val clickByRecovered: LiveData<Event<Unit>> get() = _clickByRecovered

    init {
        viewModelScope.launch(Dispatchers.IO){
            when(val res = useCase(Unit)){
                is Result.Success -> {
                    val totalCase = res.data.cases
                    val totalDeaths = res.data.deaths
                    val totalRecovered = res.data.recovered
                    val activeCase = res.data.active
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

    fun onCase(){
        _clickByCase.value = Event(Unit)
    }
    fun onDeaths(){
        _clickByDeaths.value = Event(Unit)
    }
    fun onRecovered(){
        _clickByRecovered.value = Event(Unit)
    }
}