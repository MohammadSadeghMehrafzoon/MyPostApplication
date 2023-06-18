package ir.misterdeveloper.mypostsapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.misterdeveloper.mypostsapplication.R
import ir.misterdeveloper.mypostsapplication.model.CommentsResponse


class CommentsAdapter(
    private var commentsResponse: MutableList<CommentsResponse.ResponseBodyCommentsItem>? = null,
) : RecyclerView.Adapter<CommentsAdapter.CommentVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.CommentVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_comments, null)
        return CommentVH(view)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentVH, position: Int) {
        val commentsResponse = commentsResponse?.get(position)

        holder.textViewBodyComment.text = commentsResponse!!.body
        holder.textViewBodyEmail.text = commentsResponse.email
        holder.textViewBodyName.text = commentsResponse.name
    }

    override fun getItemCount(): Int {
        return commentsResponse!!.size
    }


    inner class CommentVH(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewBodyName: AppCompatTextView = itemView.findViewById(R.id.textViewBodyName)
        var textViewBodyEmail: AppCompatTextView = itemView.findViewById(R.id.textViewBodyEmail)
        var textViewBodyComment: AppCompatTextView = itemView.findViewById(R.id.textViewBodyComment)

    }
}