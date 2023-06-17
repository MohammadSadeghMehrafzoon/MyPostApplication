package ir.misterdeveloper.mypostsapplication.model


import com.google.gson.annotations.SerializedName

class CommentsResponse : ArrayList<CommentsResponse.ResponseBodyCommentsItem>(){
    data class ResponseBodyCommentsItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("postId")
        val postId: Int
    )
}