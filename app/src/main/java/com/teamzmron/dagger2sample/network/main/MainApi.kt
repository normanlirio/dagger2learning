package com.teamzmron.dagger2sample.network.main

import com.teamzmron.dagger2sample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ) : Flowable<List<Post>>

}