package com.teamzmron.dagger2sample.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.teamzmron.dagger2sample.SessionManager
import com.teamzmron.dagger2sample.models.User
import com.teamzmron.dagger2sample.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager): ViewModel() {

    init {
        Log.v("Profile View Model", "ProfileViewmodel: view model ready...")
    }

    fun getAuthenticatedUser() : LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}