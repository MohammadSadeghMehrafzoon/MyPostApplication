package ir.misterdeveloper.mypostsapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.misterdeveloper.mypostsapplication.app.MyApplication
import ir.misterdeveloper.mypostsapplication.model.CommentsResponse
import ir.misterdeveloper.mypostsapplication.repository.AppRepository
import ir.misterdeveloper.mypostsapplication.util.Event
import ir.misterdeveloper.mypostsapplication.util.Resource
import ir.misterdeveloper.mypostsapplication.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response

class CommentsViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {


    private val data = MutableLiveData<Event<Resource<CommentsResponse>>>()
    val commentsData: LiveData<Event<Resource<CommentsResponse>>> = data


    fun getComments(postId: Int) = viewModelScope.launch {

        fetchComments(postId)
    }

    private suspend fun fetchComments(postId: Int) {
        data.postValue(Event(Resource.Loading()))
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getComments(postId)
                data.postValue(handleLoginResponse(response!!))

            } else {
                data.postValue(Event(Resource.Error("No internet connection")))

            }
        } catch (t: Throwable) {


            data.postValue(
                Event(
                    Resource.Error("Network failure")
                )
            )
        }

    }


    private fun handleLoginResponse(response: Response<CommentsResponse>): Event<Resource<CommentsResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))


    }
}
