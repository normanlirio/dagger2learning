package com.teamzmron.dagger2sample.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.teamzmron.dagger2sample.SessionManager
import com.teamzmron.dagger2sample.models.User
import com.teamzmron.dagger2sample.network.auth.AuthApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi: AuthApi, private val sessionManager: SessionManager) : ViewModel() {


    init {
        if (authApi == null) {
            Log.v("AuthApi", "AuthApi is Null")
        } else {
            Log.v("AuthApi", "AuthApi is NOT Null")
        }

    }

    fun authenticateWithId(id: Int) {
        Log.v("AuthViewModel", "authenticateWithId: Attempting to login")
        sessionManager.authenticateWithId(queryUserId(id))
    }


    fun queryUserId(id: Int) : LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(id)
                .onErrorReturn(Function {
                    val userError = User()
                    userError.id = -1
                    userError
                })
                .map(Function<User, AuthResource<User>> {
                    if(it.id == -1) {
                        AuthResource.error("could not authenticate", null)
                    } else {
                        AuthResource.authenticated(it)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }


    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()


}