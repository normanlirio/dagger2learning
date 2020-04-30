package com.teamzmron.dagger2sample.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.teamzmron.dagger2sample.R
import com.teamzmron.dagger2sample.models.Post


class PostRecyclerAdapter : Adapter<ViewHolder>() {

    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    fun setPosts(posts : List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null

        init {
            title = itemView.findViewById(R.id.title)
        }

        fun bind(post : Post) {
            title!!.text = post.title
        }

    }
}
