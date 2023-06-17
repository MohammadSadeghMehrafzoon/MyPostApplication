package ir.misterdeveloper.mypostsapplication.network

import ir.misterdeveloper.mypostsapplication.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {


        private val retrofit by lazy {

            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val apiService by lazy {
            retrofit.create(ApiService::class.java)
        }


    }



}