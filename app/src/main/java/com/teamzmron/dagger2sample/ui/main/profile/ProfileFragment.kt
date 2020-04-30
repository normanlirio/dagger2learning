package com.teamzmron.dagger2sample.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.teamzmron.dagger2sample.R
import com.teamzmron.dagger2sample.ViewModels.ViewModelProviderFactory
import com.teamzmron.dagger2sample.models.User
import com.teamzmron.dagger2sample.ui.auth.AuthResource
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    private lateinit var viewModel: ProfileViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(context!!, "Profile Fragment", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v("ProfileFragment", "ProfileFragment: created...")

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserDetails(data: User?) {
        email.text = data!!.email
        username.text = data!!.username
        website.text = data!!.website
    }
}