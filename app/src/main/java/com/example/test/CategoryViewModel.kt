package com.example.test

import ApiClient
import ApiService
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel :ViewModel() {
    private val _categoryList = mutableStateListOf<Category>()
    var errorMessage: String by mutableStateOf("")
    val categoryList: List<Category>
        get() = _categoryList
    fun getCategories() {
        viewModelScope.launch {
//            val apiService =ApiClient.apiService.
            try {
                Log.v("myLog","getCategories-->try")
                _categoryList.clear()
                _categoryList.addAll(ApiClient.apiService.getCategories().data)
                Log.v("myLog",_categoryList.toString())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.v("myLog","getCategories-->catch")
            }
        }
    }
}


