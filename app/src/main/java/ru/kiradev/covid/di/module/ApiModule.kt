package ru.kiradev.covid.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiradev.covid.mvp.model.api.IDataSource
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.BASE_URL
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.CACHE_DIRECTORY_NAME
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.CACHE_SIZE
import ru.kiradev.covid.mvp.model.network.INetworkStatus
import ru.kiradev.covid.ui.App
import ru.kiradev.covid.ui.network.AndroidNetworkStatus
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun api(gson: Gson, client: OkHttpClient): IDataSource =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)

    @Singleton
    @Provides
    fun client(app: App) = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .cache(app.getExternalFilesDir(CACHE_DIRECTORY_NAME)?.let { Cache(it, CACHE_SIZE) })
        .build()
}