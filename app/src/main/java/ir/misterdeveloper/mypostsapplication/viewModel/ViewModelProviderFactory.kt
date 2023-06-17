package ir.misterdeveloper.mypostsapplication.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.misterdeveloper.mypostsapplication.repository.AppRepository


class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(app, appRepository) as T
        }

        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(app, appRepository) as T
        }


        throw IllegalArgumentException("Unknown class name")
    }

}
