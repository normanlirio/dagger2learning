package com.teamzmron.dagger2sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.teamzmron.dagger2sample.ui.auth.AuthActivity
import com.teamzmron.dagger2sample.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            if(it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.LOADING -> {

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {

                        Log.v("AUTHENTICATED", "Login Success! ${it.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {

                        Toast.makeText(this, "${it.message} Did you enter a number between a 1 and 10?", Toast.LENGTH_LONG).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        })
    }


    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}