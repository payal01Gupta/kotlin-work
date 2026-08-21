package com.example.mykotlinwork.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinwork.R
import com.example.mykotlinwork.models.PostModel
class PostAdapter : RecyclerView.Adapter<PostAdapter.UserViewHolder>() {

    private val postList = mutableListOf<PostModel>()

    fun setPosts(post: List<PostModel>) {

        postList.clear()
        postList.addAll(post)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {

        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvUserID: TextView = itemView.findViewById(R.id.tvUserID)

        private val tvID: TextView = itemView.findViewById(R.id.tvID)

        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvBody: TextView = itemView.findViewById(R.id.tvBody)

        fun bind(posts: PostModel) {

            tvUserID.text = posts.userId.toString()
            tvID.text = posts.id.toString()
            tvTitle.text = posts.title
            tvBody.text = posts.body
        }
    }
}