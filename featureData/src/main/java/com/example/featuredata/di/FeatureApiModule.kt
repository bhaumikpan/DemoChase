package com.example.featuredata.di

import com.example.featuredata.api.FeatureApi

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object FeatureApiModule {

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://data.cityofnewyork.us/") // base url
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun providesFeatureApi(): FeatureApi = retrofit.create(FeatureApi::class.java)
}