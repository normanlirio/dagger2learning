package com.teamzmron.dagger2sample.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamzmron.dagger2sample.R
import com.teamzmron.dagger2sample.ViewModels.ViewModelProviderFactory
import com.teamzmron.dagger2sample.ui.main.Resource
import com.teamzmron.dagger2sample.util.VerticalSpaceItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private  var postViewModel: PostsViewModel ? = null

    @Inject
    lateinit var adapter: PostRecyclerAdapter

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postViewModel = ViewModelProvider(this,providerFactory).get(PostsViewModel::class.java)
        Toast.makeText(context!!, "Post Fragment", Toast.LENGTH_LONG).show()
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        postViewModel!!.observePosts().removeObservers(viewLifecycleOwner)
        postViewModel!!.observePosts().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        Log.v("PostFragment", "subscribeObservers: Loading...")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.v("PostFragment", "subscribeObservers: getting posts...")
                        adapter.setPosts(it.data!!)
                    }
                    Resource.Status.ERROR -> {
                        Log.v("PostFragment", "subscribeObservers: Error : ${it.message}")
                    }
                }
            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(context)
        val itemDecoration = VerticalSpaceItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = adapter
    }
}