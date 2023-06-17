package ir.misterdeveloper.mypostsapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.misterdeveloper.mypostsapplication.R
import ir.misterdeveloper.mypostsapplication.model.PostResponse

class PostsAdapter(
    private var context: Context,
    private var postsResponse: MutableList<PostResponse.ResponseBodyPostItem>? = null,
) : RecyclerView.Adapter<PostsAdapter.PostVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_posts, null)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostVH, position: Int) {
        val postsResponse = postsResponse?.get(position)


        holder.textViewTitle.text = postsResponse!!.body

    }

    override fun getItemCount(): Int {
        return postsResponse!!.size
    }

    inner class PostVH(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewComment: AppCompatTextView = itemView.findViewById(R.id.textViewComment)
        var textViewTitle: AppCompatTextView = itemView.findViewById(R.id.textViewTitle)

    }
}