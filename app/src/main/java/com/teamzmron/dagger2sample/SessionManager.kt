package com.teamzmron.dagger2sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.teamzmron.dagger2sample.models.User
import com.teamzmron.dagger2sample.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor() {

    private var cachedUser: MediatorLiveData<AuthResource<User>>? = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>> ){
        if(cachedUser != null) {
            cachedUser?.value = AuthResource.loading(null)
            cachedUser?.addSource(source, Observer {
                cachedUser?.value = it
                cachedUser?.removeSource(source)
            })
        }
    }

     fun logOut() {
        Log.v("SessionManager","logOut: logging out...")
        cachedUser?.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>> = cachedUser!!
}