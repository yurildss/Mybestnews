package com.example.mybestnews.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybestnews.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFlowViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(GreetingUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val userPreferences  = userPreferencesRepository.getUserPreferences()
            userPreferences.collect {
                _uiState.value = GreetingUIState(it.newUser)
            }
        }
    }

}
data class GreetingUIState(
    val newUser: Boolean = false
)
