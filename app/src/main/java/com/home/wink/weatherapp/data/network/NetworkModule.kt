package com.home.wink.weatherapp.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(
                        HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
    }

    @Provides
    @Singleton
    fun provideForecastApi(gson: Gson, okHttpClient: OkHttpClient): ForecastApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(ForecastApi::class.java)
    }
}