package com.example.featuredomian.repository

import com.example.featuredomian.models.School

interface SchoolRepo {
    suspend fun getSchoolList(): List<School>
    suspend fun getSchoolDetails(id: String): School
}