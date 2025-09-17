package com.example.mybestnews.screen.newsPreferences

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserNewsPreferencesViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

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

    fun updateExpandedLanguageOptions(){
        _uiState.value = _uiState.value.copy(expandedLanguageOptions = !_uiState.value.expandedLanguageOptions)
    }

    fun saveOptions(onSaved: () -> Unit){
        viewModelScope.launch {
            try {
                userPreferencesRepository.saveFavoriteCountry(listOf(_uiState.value.selectedCountryItem))
                userPreferencesRepository.saveFavoriteTags(listOf(_uiState.value.selectedCategoryItem))
                userPreferencesRepository.saveFavoriteLanguage(listOf(_uiState.value.selectedLanguageItem))
                userPreferencesRepository.updateNewUserStatus(true)
                onSaved()
            }catch (
                e: Exception
            ){
                Log.e("Error", e.toString())
            }
        }
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