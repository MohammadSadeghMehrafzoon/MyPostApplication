package ir.misterdeveloper.mypostsapplication.model


import com.google.gson.annotations.SerializedName

class PostResponse : ArrayList<PostResponse.ResponseBodyPostItem>(){
    data class ResponseBodyPostItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}