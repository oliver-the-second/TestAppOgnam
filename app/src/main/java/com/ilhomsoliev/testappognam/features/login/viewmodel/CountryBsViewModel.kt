package com.ilhomsoliev.testappognam.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.testappognam.app.navigation.country
import com.ilhomsoliev.testappognam.shared.country.Country
import com.ilhomsoliev.testappognam.shared.country.CountryManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class CountryBsViewModel(

    private val countryManager: CountryManager,

): ViewModel() {
    
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()
    
    suspend fun changeQuery(query: String) {
        _query.emit(query)
    }
    
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    suspend fun loadCountries() {
        _countries.emit(
            countryManager.getCountries()
        )
    }

    val countries = _countries
        .combine(country) { list, current -> // Изменение порядка
            val default = countryManager.defaultCountry
            (if(default == current) listOf(default) else listOf(default, current)) +
                    (list - default - current)
        }
        .combine(query.debounce(250)) { list, str -> // Поиск
            list.filter {
                it.name.contains(str, true)
                        || it.code.contains(str, true)
                        || it.phoneDial.contains(str, true)
                        || it.clearPhoneDial.contains(str, true)
            }
        }.state(_countries.value)

    private fun <T> Flow<T>.state(
        initial: T,
        started: SharingStarted = SharingStarted.Lazily,
    ): StateFlow<T> = stateIn(viewModelScope, started, initial)
}