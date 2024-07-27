package com.retro_in_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    private val _dataState = MutableStateFlow<DataState<List<ResponseData>>>(DataState.Loading)
    val dataState: StateFlow<DataState<List<ResponseData>>> = _dataState

    init {
        fetchData()
    }

     fun fetchData() {
        viewModelScope.launch {
            try {
                val data = repository.getData()
                _dataState.value = DataState.Success(data)
            } catch (e: Exception) {
                _dataState.value = DataState.Error(e)
            }
        }
    }
}