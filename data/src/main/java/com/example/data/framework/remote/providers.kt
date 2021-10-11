package com.example.data.framework.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

/*
    in this particular case we have only one api - but for the scaling purpose I prefer to have
    this func to provide instances of the retrofit
 */
fun <T> provideApi(
    clazz: Class<T>,
    baseUrl: String,
): T {
    val mediaType = MediaType.parse(MediaTypes.JSON.value) ?: error("Cannot get media type")
    val converterFactory = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory(mediaType)

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .build()
        .create(clazz)
}

/*
    this class is redundant in this case but in general, we have more than 1 media type
 */
enum class MediaTypes(val value: String) {
    JSON("application/json")
}
