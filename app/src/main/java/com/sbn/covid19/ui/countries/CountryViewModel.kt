package com.sbn.covid19.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbn.covid19.shared.db.CovidCountry
import com.sbn.covid19.shared.domain.country.CovidCountryUseCase
import com.sbn.covid19.shared.domain.covid.CountryUseCase
import com.sbn.covid19.shared.result.Event
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.util.Filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class CountryViewModel @Inject constructor(private val remoteUseCase: CountryUseCase, private val listUseCase: CovidCountryUseCase)  : ViewModel(),OnItemSelected {
    val list = MutableLiveData<List<CovidCountry>>()
    private val _onItemSelected = MutableLiveData<Event<CovidCountry>>()
    val onItemSelected: LiveData<Event<CovidCountry>> get() = _onItemSelected


    @InternalCoroutinesApi
    fun updateList(name: String?, filter: Filter = Filter.DEFAULT){
        viewModelScope.launch {
            val parameters = Pair(name,filter)
            listUseCase(parameters).collect {
                when(it){
                    is Result.Success -> list.postValue(it.data)
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }

            }
        }
    }

    override fun onSelect(item: CovidCountry) {
        _onItemSelected.value = Event(item)
    }

    fun remoteData(){
        viewModelScope.launch(Dispatchers.IO){
            when(remoteUseCase(Unit)){
                is Result.Success -> {}
                is Result.Error -> {}
                is Result.Loading -> {}
            }
        }
    }

}