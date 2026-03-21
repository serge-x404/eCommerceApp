package dev.serge.ecommerceapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.serge.ecommerceapp.model.Category
import dev.serge.ecommerceapp.repository.FireStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: FireStoreRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())

    val categories: StateFlow<List<Category>> get() = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            repository.getCategoriesFlow()
                .catch {
                    println("Error in flow")
                }
                .collect {categories ->
                    _categories.value = categories
                    println("Categories updated in viewModel")
                }
        }
    }
}