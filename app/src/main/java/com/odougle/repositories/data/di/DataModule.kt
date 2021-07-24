package com.odougle.repositories.data.di

import retrofit2.Retrofit

object DataModule {
    private inline fun <reified  T> createServices() : T{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
            .create(T::class.java)
    }
}