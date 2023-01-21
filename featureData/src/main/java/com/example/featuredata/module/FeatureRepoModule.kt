package com.example.featuredata.module

import com.example.featuredata.impl.FeatureRepoImpl
import com.example.featuredomian.repository.SchoolRepo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FeatureRepoModule {

    @Binds
    fun FeatureRepoImpl.bindFeatureRepository(): SchoolRepo
}