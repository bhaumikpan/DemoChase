package com.example.featureui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featuredata.api.FeatureApi
import com.example.featuredomian.models.School
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val api: FeatureApi
): ViewModel() {

    private val _dataList = MutableLiveData<List<School>>()
    val dataList: LiveData<List<School>>
        get() = _dataList

    fun getData() {
        val response = mutableListOf<School>()
        viewModelScope.launch {
            api.getSchoolList()
                .runCatching {
                    response.addAll(this)
                }.onSuccess {
                    _dataList.value = response
                    // Send analytic data

                }.onFailure {
                    // Send analytic data
                }
        }
    }
}