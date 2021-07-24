package com.odougle.repositories.data.di

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

object DataModule {

    private const val OK_HTTP = "OkHttp"

    private fun networkModules() : Module{
        return module {
            //always will be the same instance
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

        }
    }

    private inline fun <reified  T> createServices() : T{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
            .create(T::class.java)
    }
}