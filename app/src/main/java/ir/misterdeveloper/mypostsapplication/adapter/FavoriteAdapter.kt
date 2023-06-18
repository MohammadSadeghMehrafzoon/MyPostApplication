package ir.misterdeveloper.mypostsapplication.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ir.misterdeveloper.mypostsapplication.R
import ir.misterdeveloper.mypostsapplication.database.AppDatabase
import ir.misterdeveloper.mypostsapplication.database.Favorite



class FavoriteAdapter(
    private var context: Context,
    private var favoritePost: MutableList<Favorite>?,
    private val appDatabase: AppDatabase
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_posts, null)
        return FavoriteVH(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteVH, position: Int) {
        val favorite = favoritePost?.get(position)

        holder.imageViewBookMark.setImageResource(R.drawable.ic_baseline_bookmark_24)


        holder.textViewTitle.text = favorite!!.titlePost
        holder.textViewComment.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("postId", favorite.postId)
            Navigation.findNavController(it).navigate(R.id.detailsPostFragment, bundle)
        }


        holder.imageViewBookMark.setOnClickListener {


            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setMessage(R.string.text_delete_favorite)
            alertDialog.setPositiveButton(R.string.yes) { dialogInterface, i ->

                appDatabase.daoDatabase().deleteFavoriteById(favorite.id)
                refresh(position)

                dialogInterface.dismiss()

            }


            alertDialog.setNegativeButton(R.string.no) { dialogInterface, i ->

                dialogInterface.dismiss()
            }

            alertDialog.show()



        }


    }


    override fun getItemCount(): Int {
        return favoritePost!!.size
    }

    private fun refresh(position: Int) {

        favoritePost!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, favoritePost!!.size)
        notifyDataSetChanged()
    }


    inner class FavoriteVH(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewComment: AppCompatTextView = itemView.findViewById(R.id.textViewComment)
        var textViewTitle: AppCompatTextView = itemView.findViewById(R.id.textViewTitle)
        var imageViewBookMark: AppCompatImageView = itemView.findViewById(R.id.imageViewBookMark)


    }
}