package com.example.test

import ApiClient
import ApiService
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel :ViewModel() {
    private val _categoryList = mutableStateListOf<Category>()
    var errorMessage: String by mutableStateOf("")
    private var _isLoading = mutableStateOf(true)
    val categoryList: List<Category>
        get() = _categoryList
    val isLoading : State<Boolean>
        get() = _isLoading
    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
//            val apiService =ApiClient.apiService.
            try {
                val data = ApiClient.apiService.getCategories().data
                _categoryList.clear()
                _categoryList.addAll(data)
                _isLoading.value=false;
                Log.v("myLog","isLoading1-->" + _isLoading.value)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                _isLoading.value=false;
                Log.v("myLog","getCategories-->catch")
            }
        }
    }
}


