package com.ilhomsoliev.testappognam.features.login.presentation.login.country

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation._country
import com.ilhomsoliev.testappognam.features.login.viewmodel.CountryBsViewModel
import com.ilhomsoliev.testappognam.shared.country.Country
import kotlinx.coroutines.launch

@Composable
fun CountryBs(vm: CountryBsViewModel, navController: NavController) {

    val countries by vm.countries.collectAsState()
    val searchText by vm.query.collectAsState()
    val scope = rememberCoroutineScope()

    var searchState by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) { vm.loadCountries() }

    CountryBottomSheetContent(
        CountryBottomSheetState(searchText, searchState, countries),
        Modifier, object : CountryCallBack {

            override fun onCountrySelect(country: Country) {
                scope.launch {
                    _country.emit(country)
                    navController.popBackStack()
                }
            }

            override fun onSearchTextChange(text: String) {
                scope.launch { vm.changeQuery(text) }
            }

            override fun onSearchStateChange() {
                scope.launch {
                    vm.changeQuery("")
                }
            }
        }
    )
}