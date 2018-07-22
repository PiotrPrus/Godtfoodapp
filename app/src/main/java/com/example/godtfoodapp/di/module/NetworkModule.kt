package com.example.godtfoodapp.di.module

import com.example.godtfoodapp.network.GodtApi
import com.example.godtfoodapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGodtApi(retrofit: Retrofit): GodtApi {
        return retrofit.create(GodtApi::class.java)
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
    private val client: OkHttpClient = OkHttpClient.Builder().apply { this.addInterceptor(interceptor) }.build()

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build()
    }
}