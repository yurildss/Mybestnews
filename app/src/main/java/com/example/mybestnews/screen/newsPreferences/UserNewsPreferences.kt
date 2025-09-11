package com.example.mybestnews.screen.newsPreferences

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun UserNewsPreferencesScreen(
    modifier: Modifier = Modifier,
    viewModel: UserNewsPreferencesViewModel = hiltViewModel(),
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    PreferencesForm(
        uiState = uiState.value,
        saveOptions = viewModel::saveOptions,
        onSelectedCategory = viewModel::updateSelectedCategory,
        onSelectedLanguage = viewModel::updateSelectedLanguage,
        onSelectedCountry = viewModel::updateSelectedCountry,
        updateCategoryExpandedOptions = viewModel::updateExpandedCategoryOptions,
        updateLanguageExpandedOptions = viewModel::updateExpandedLanguageOptions,
        updateCountryExpandedOptions = viewModel::updateExpandedCountryOptions,
        )
}

@Composable
fun PreferencesForm(
    modifier: Modifier = Modifier,
    updateCountryExpandedOptions: () -> Unit,
    updateCategoryExpandedOptions: () -> Unit,
    updateLanguageExpandedOptions: () -> Unit,
    onSelectedCountry: (String) -> Unit,
    onSelectedCategory: (String) -> Unit,
    onSelectedLanguage: (String) -> Unit,
    uiState: NewsPreferencesUIState,
    saveOptions: () -> Unit
    ){
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "User preferences",
            modifier = Modifier.padding(bottom = 16.dp),
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp
        )
        FilterCountryCard(
            label = "country",
            updateExpandedOptions = updateCountryExpandedOptions,
            expandedDropMenu = uiState.expandedCountryOptions,
            list = uiState.listOfCountries,
            selectedItem = uiState.selectedCountryItem,
            selectedIndex = { index -> /*TODO*/ },
            onSelected = onSelectedCountry
        )
        FilterCategoryCard(
            label = "category",
            updateExpandedOptions = updateCategoryExpandedOptions,
            expandedDropMenu = uiState.expandedCategoryOptions,
            list = uiState.listOfCategories,
            selectedItem = uiState.selectedCategoryItem,
            selectedIndex = { index -> /*TODO*/ },
            onSelected = onSelectedCategory
        )
        Button(
            onClick = saveOptions,
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.padding(24.dp).fillMaxWidth(0.6f).align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Create")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCountryCard(
    label: String,
    updateExpandedOptions: () -> Unit,
    expandedDropMenu: Boolean,
    list: List<String>,
    selectedItem: String,
    selectedIndex: (index: Int) -> Unit,
    onSelected: (item: String) -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedDropMenu,
            onExpandedChange = { updateExpandedOptions() },
            modifier = Modifier
                .fillMaxWidth(0.85F)
                .padding(top = 10.dp)
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {

                },
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .testTag(label),
                label = {
                    Text(
                        "Select the $label of the news",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons
                            .AutoMirrored
                            .Filled
                            .KeyboardArrowRight,
                        contentDescription = ""
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expandedDropMenu,
                onDismissRequest = {
                    updateExpandedOptions()
                }
            ){
                list.forEach {
                        item ->
                    DropdownMenuItem(
                        text = { Text(text = item, color = MaterialTheme.colorScheme.primary) },
                        onClick = {
                            selectedIndex(list.indexOf(item))
                            onSelected(item)
                            updateExpandedOptions()
                        },
                        modifier = Modifier.testTag(item)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCategoryCard(
    label: String,
    updateExpandedOptions: () -> Unit,
    expandedDropMenu: Boolean,
    list: List<String>,
    selectedItem: String,
    selectedIndex: (index: Int) -> Unit,
    onSelected: (item: String) -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedDropMenu,
            onExpandedChange = { updateExpandedOptions() },
            modifier = Modifier
                .fillMaxWidth(0.85F)
                .padding(top = 10.dp)
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {

                },
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .testTag(label),
                label = {
                    Text(
                        "Select the $label of the news",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons
                            .AutoMirrored
                            .Filled
                            .KeyboardArrowRight,
                        contentDescription = ""
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expandedDropMenu,
                onDismissRequest = {
                    updateExpandedOptions()
                }
            ){
                list.forEach {
                        item ->
                    DropdownMenuItem(
                        text = { Text(text = item, color = MaterialTheme.colorScheme.primary) },
                        onClick = {
                            selectedIndex(list.indexOf(item))
                            onSelected(item)
                            updateExpandedOptions()
                        },
                        modifier = Modifier.testTag(item)
                    )
                }
            }
        }
    }
}