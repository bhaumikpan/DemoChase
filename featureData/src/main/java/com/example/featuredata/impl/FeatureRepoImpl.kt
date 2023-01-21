package com.example.featuredata.impl

import com.example.featuredata.api.FeatureApi
import com.example.featuredata.di.IoDispatcher
import com.example.featuredomian.models.School
import com.example.featuredomian.repository.SchoolRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeatureRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: FeatureApi
) : SchoolRepo {
    override suspend fun getSchoolList(): List<School> {
        val emptyList = mutableListOf<School>()
        withContext(dispatcher) {
            runCatching {
                api.getSchoolList()
            }.onSuccess { response ->
                emptyList.addAll(response)
            }.onFailure {

            }
        }
        return emptyList
    }

    override suspend fun getSchoolDetails(id: String): School {
        val emptyList = mutableListOf<School>()
        withContext(dispatcher) {
            runCatching {
                api.getSchoolDetails(id)
            }.onSuccess { _ ->
                val answer = emptyList.single {
                    it.dbn == id
                }
                emptyList[0] = answer
            }.onFailure {

            }
        }
        return emptyList[0]
    }
}