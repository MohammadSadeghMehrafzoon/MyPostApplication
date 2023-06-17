package ir.misterdeveloper.mypostsapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.misterdeveloper.mypostsapplication.app.MyApplication
import ir.misterdeveloper.mypostsapplication.model.PostResponse
import ir.misterdeveloper.mypostsapplication.repository.AppRepository
import ir.misterdeveloper.mypostsapplication.util.Resource
import ir.misterdeveloper.mypostsapplication.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class PostsViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {



    val postsData: MutableLiveData<Resource<PostResponse>> = MutableLiveData()

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        fetchPosts()
    }

    private suspend fun fetchPosts() {
        postsData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getPosts()
                postsData.postValue(handleServiceResponse(response))
            } else {
                postsData.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> postsData.postValue(
                    Resource.Error(
                      "Network failure"

                    )
                )
                else -> postsData.postValue(
                    Resource.Error(
                        "Conversion error"
                        )

                )
            }
        }
    }

    private fun handleServiceResponse(response: Response<PostResponse>): Resource<PostResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}