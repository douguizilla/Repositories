package com.odougle.repositories.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.odougle.repositories.data.services.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP = "OkHttp"

    private fun networkModules() : Module{
        return module {
        //always will be the same instance

            //Cliente
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            //Factory to convert Json
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            //Service
            single {
                createService<GitHubService>(get(), get())
            }

        }
    }

    private inline fun <reified  T> createService(client: OkHttpClient, factory: GsonConverterFactory) : T{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}