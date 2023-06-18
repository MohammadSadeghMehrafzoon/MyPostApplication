package ir.misterdeveloper.mypostsapplication.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ir.misterdeveloper.mypostsapplication.R
import ir.misterdeveloper.mypostsapplication.database.AppDatabase
import ir.misterdeveloper.mypostsapplication.database.Favorite
import ir.misterdeveloper.mypostsapplication.model.PostResponse
import ir.misterdeveloper.mypostsapplication.util.toast


class PostsAdapter(
    private var context: Context,
    private var postsResponse: MutableList<PostResponse.ResponseBodyPostItem>? = null,
    private val appDatabase: AppDatabase

) : RecyclerView.Adapter<PostsAdapter.PostVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_posts, null)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostVH, position: Int) {
        val postItem = postsResponse?.get(position)


        for (i in 0 until postsResponse!!.size){

            val favorite: Boolean = appDatabase.daoDatabase().isExists(postItem!!.id)

            if(favorite){

                holder.imageViewBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24)

            }else{
                holder.imageViewBookMark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }


        }



        holder.textViewTitle.text = postItem!!.body
        holder.textViewComment.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("postId", postItem.userId)
            Navigation.findNavController(it).navigate(R.id.detailsPostFragment, bundle)
        }

        holder.imageViewBookMark.setOnClickListener {


            val checkFavoritePost: Boolean = appDatabase.daoDatabase().isExists(postItem.id)

            if(checkFavoritePost){

                appDatabase.daoDatabase().deleteFavoriteByIdPost(postItem.id)
                holder.imageViewBookMark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)

            }else{

                val favorite = Favorite()
                favorite.postId = postItem.userId
                favorite.titlePost = postItem.body
                favorite.idPost = postItem.id

                val result = appDatabase.daoDatabase().insertFavoritePost(favorite)

                if (result > 0) {

                    holder.imageViewBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    context.toast(context, "this post added bookmark")

                }
            }




        }


    }

    override fun getItemCount(): Int {
        return postsResponse!!.size
    }

    inner class PostVH(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewComment: AppCompatTextView = itemView.findViewById(R.id.textViewComment)
        var textViewTitle: AppCompatTextView = itemView.findViewById(R.id.textViewTitle)
        var imageViewBookMark: AppCompatImageView = itemView.findViewById(R.id.imageViewBookMark)

    }
}