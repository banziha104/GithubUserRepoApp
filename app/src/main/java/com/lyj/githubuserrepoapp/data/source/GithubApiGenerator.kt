package com.lyj.githubuserrepoapp.data.source

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit


/**
 * Github 관련 API 서비스를 생성하는 구현 객체
 */
class GithubApiGenerator(
    private val client: OkHttpClient,
    private val callAdapter: CallAdapter.Factory,
    private val converter: Converter.Factory
) : ServiceGenerator {

    companion object {
        val HEADER_MAP: Map<String, String> = mapOf(
            "Accept" to "application/vnd.github.v3+json"
        )
        const val BASE_URL = "https://api.github.com"
    }

    override fun <T> generateService(
        service: Class<T>,
    ): T = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(callAdapter)
        .addConverterFactory(converter)
        .build()
        .create(service)
}

/**
 * Github 관련 API 서비스를 생성하는 추상 객체
 */
interface ServiceGenerator {
    fun <T> generateService(service: Class<T>): T
}
