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
class SchoolDetailViewModel @Inject constructor(
    private val api: FeatureApi
): ViewModel() {

    private val _schoolData = MutableLiveData<School>()
    val schoolData: LiveData<School>
        get() = _schoolData

    fun getData(id: String) {
        val response = mutableListOf<School>()
        viewModelScope.launch {
            api.getSchoolDetails(id)
                .runCatching {
                    response.addAll(this)
                }.onSuccess {
                    when (response.size > 0) {
                      true ->  _schoolData.value = response[0]
                      else -> {
                          // couldn't find school
                          _schoolData.value = School("XXX", "dummy: no school here, server return no result",
                              "Server returned empty list", "no school here", "",
                              "", "", "")
                      }
                    }
                    // Send analytic data
                }.onFailure {
                    // Send analytic data
                }
        }
    }
}