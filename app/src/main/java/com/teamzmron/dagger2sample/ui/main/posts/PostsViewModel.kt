package com.teamzmron.dagger2sample.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.teamzmron.dagger2sample.SessionManager
import com.teamzmron.dagger2sample.models.Post
import com.teamzmron.dagger2sample.network.main.MainApi
import com.teamzmron.dagger2sample.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi): ViewModel() {

    private var posts: MediatorLiveData<Resource<List<Post>>> ? = null

    init {
        Log.v("PostViewMOdel", "PostViewModel: working...")
    }

    fun observePosts() : LiveData<Resource<List<Post>>> {

        if(posts == null) {
           posts = MediatorLiveData()
            posts!!.value = Resource.loading(null)

            val source : LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                    .onErrorReturn {
                        var post = Post()
                        post.id = -1
                        var postList = ArrayList<Post>()
                        postList.add(post)
                        postList
                    }
                    .map {
                        if(it.isNotEmpty()) {
                            if(it[0].id == -1) {
                                Resource.error("Something went wrong.", null)
                            }
                        }
                        Resource.success(it)
                    }
                    .subscribeOn(Schedulers.io())
            )
            posts!!.addSource(source, Observer {
                posts!!.value = it
                posts!!.removeSource(source)
            })
        }
        return posts!!
    }
}