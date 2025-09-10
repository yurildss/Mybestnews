package com.example.mybestnews.screen.newsPreferences

import androidx.lifecycle.ViewModel
import com.example.mybestnews.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserNewsPreferencesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(NewsPreferencesUIState())
    val uiState: StateFlow<NewsPreferencesUIState> = _uiState.asStateFlow()

    fun updateSelectedCountry(country: String){
        _uiState.value = _uiState.value.copy(selectedCountryItem = country)
    }

    fun updateExpandedCountryOptions(){
        _uiState.value = _uiState.value.copy(expandedCountryOptions = !_uiState.value.expandedCountryOptions)
    }

    fun updateSelectedCategory(category: String){
        _uiState.value = _uiState.value.copy(selectedCategoryItem = category)
    }

    fun updateExpandedCategoryOptions(){
        _uiState.value = _uiState.value.copy(expandedCategoryOptions = !_uiState.value.expandedCategoryOptions)
    }

    fun updateSelectedLanguage(language: String){
        _uiState.value = _uiState.value.copy(selectedLanguageItem = language)
    }

}

data class NewsPreferencesUIState(
    val listOfCountries: List<String> = listOf("ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch",
        "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in",
        "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt",
        "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"),
    val selectedCountryItem: String = "",
    val expandedCountryOptions: Boolean = false,
    val listOfCategories: List<String> = listOf("business", "entertainment", "general",
        "health", "science", "sports", "technology"),
    val listOfLanguages: List<String> = listOf("ar", "de", "en", "es", "fr", "he",
        "it", "nl", "no", "pt", "ru", "sv", "ud", "zh"),
    val selectedCategoryItem: String = "",
    val expandedCategoryOptions: Boolean = false,
    val selectedLanguageItem: String = "",
    val expandedLanguageOptions: Boolean = false

)