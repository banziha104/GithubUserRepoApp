package com.lyj.githubuserrepoapp.module

import android.content.Context
import com.lyj.githubuserrepoapp.R
import com.lyj.githubuserrepoapp.common.longToast
import com.lyj.githubuserrepoapp.data.source.GithubApiGenerator
import com.lyj.githubuserrepoapp.data.source.ServiceGenerator
import com.lyj.githubuserrepoapp.data.source.api.interceptor.GithubHeaderInterceptor
import com.lyj.githubuserrepoapp.data.source.api.interceptor.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providerConvertFactory(): Converter.Factory = GsonConverterFactory
        .create()

    @Provides
    @Singleton
    fun providerOkHttpClient(@ApplicationContext context : Context): OkHttpClient = OkHttpClient.Builder().let {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        it
            .addInterceptor(logger)
            .addInterceptor(NetworkConnectionInterceptor(context,NetworkConnectionInterceptor.OnCheckNetworkConnection { isNetworkConnetable ->
                if (!isNetworkConnetable)
                MainScope().launch(Dispatchers.Main) {
                    context.longToast(R.string.network_not_reachable)
                }
            }))
            .addInterceptor(GithubHeaderInterceptor(GithubApiGenerator.HEADER_MAP))
            .connectTimeout(20, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceGenerator(
        client: OkHttpClient,
        callAdapter: CallAdapter.Factory,
        converter: Converter.Factory
    ): ServiceGenerator = GithubApiGenerator(client, callAdapter, converter)
}