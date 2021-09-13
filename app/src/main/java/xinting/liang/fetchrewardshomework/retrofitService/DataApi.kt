package xinting.liang.fetchrewardshomework.retrofitService

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
//hiring.json

// Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit Object
private val retrofitObject = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// API Interface
interface DataApiService {
    @GET("hiring.json")
    fun getUserData (): Call<List<WireItem>>
}

object DataRetrofitApi{
    val retrofitService : DataApiService by lazy {
        retrofitObject.create(DataApiService::class.java)
    }
}
