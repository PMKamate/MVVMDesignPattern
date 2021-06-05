package com.manektech.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manektech.data.local.AppDatabase
import com.manektech.data.local.ImageDao
import com.manektech.data.local.RestaurantDao
import com.manektech.data.remote.RestaurantRemoteDataSource
import com.manektech.data.remote.RestaurantService
import com.manektech.data.repository.ApiSettings
import com.manektech.data.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = ApiSettings.BASE_API_URL

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://205.134.254.135/~mobile/interview/public/api/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    /* @Singleton
     @Provides
     fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
         val loggingInterceptor = HttpLoggingInterceptor()
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
         OkHttpClient.Builder()
             .addInterceptor(loggingInterceptor)
             .build()
     } else {
         val interceptor = certificateTransparencyInterceptor {
             +ApiSettings.APP_DOMAIN
         }
         OkHttpClient.Builder().apply {
             addNetworkInterceptor(interceptor)
         }
     }*/

    /* @Singleton
     @Provides
     fun provideOkHttpClient() = if (BuildConfig.DEBUG){
         val loggingInterceptor =HttpLoggingInterceptor()
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
         OkHttpClient.Builder()
             .addInterceptor(loggingInterceptor)
             .build()
     }else{
         val interceptor = certificateTransparencyInterceptor {
             + ApiSettings.APP_DOMAIN
         }
         OkHttpClient.Builder().
             addNetworkInterceptor(interceptor).build()
     }*/


    /*  @Singleton
      @Provides
      fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
          .addConverterFactory(JacksonConverterFactory.create())
          .baseUrl(BASE_URL)
          .client(okHttpClient)
          .build()*/

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRestaurantService(retrofit: Retrofit): RestaurantService = retrofit.create(
        RestaurantService::class.java
    )

    @Singleton
    @Provides
    fun provideRestaurantRemoteDataSource(restaurantService: RestaurantService) =
        RestaurantRemoteDataSource(restaurantService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRestaurantDao(db: AppDatabase) = db.restaurantDao()

    @Singleton
    @Provides
    fun provideImageDao(db: AppDatabase) = db.imageDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RestaurantRemoteDataSource,
        restaurantDao: RestaurantDao,
        imageDao: ImageDao
    ) =
        RestaurantRepository(remoteDataSource, restaurantDao, imageDao)

}