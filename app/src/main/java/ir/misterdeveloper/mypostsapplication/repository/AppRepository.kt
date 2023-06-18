package ir.misterdeveloper.mypostsapplication.repository

import ir.misterdeveloper.mypostsapplication.network.RetrofitInstance

class AppRepository {



    suspend fun getPosts() = RetrofitInstance.apiService.getPosts()

    suspend fun getComments(postId: Int) = RetrofitInstance.apiService.getComments(postId)
}