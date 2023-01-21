package com.example.featuredata.api

import com.example.featuredomian.models.School
import retrofit2.http.GET
import retrofit2.http.Query

interface FeatureApi {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolList(): List<School>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolDetails(@Query("dbn") id: String): List<School>
}