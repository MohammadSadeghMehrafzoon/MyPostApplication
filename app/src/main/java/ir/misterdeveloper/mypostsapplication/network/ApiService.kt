package ir.misterdeveloper.mypostsapplication.network

import ir.misterdeveloper.mypostsapplication.model.CommentsResponse
import ir.misterdeveloper.mypostsapplication.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<PostResponse>


    @GET("posts")
    suspend fun getComments(
        @Query("postId") postId: Int
    ): Response<CommentsResponse>
}