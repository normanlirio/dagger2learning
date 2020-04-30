package com.teamzmron.dagger2sample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.teamzmron.dagger2sample.R
import com.teamzmron.dagger2sample.ViewModels.ViewModelProviderFactory
import com.teamzmron.dagger2sample.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*

import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_button.setOnClickListener(this)

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()

        subsribeObserver()
    }

    private fun subsribeObserver() {
        authViewModel.observeAuthState().observe(this, Observer {
            if(it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.v("AUTHENTICATED", "Login Success! ${it.data?.email}")
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "${it.message} Did you enter a number between a 1 and 10?", Toast.LENGTH_LONG).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.login_button -> {
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        authViewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }
}
