package com.example.mykotlinwork.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinwork.R
import com.example.mykotlinwork.models.UsersModel
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = mutableListOf<UsersModel>()

    fun setUsers(users: List<UsersModel>) {

        userList.clear()
        userList.addAll(users)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {

        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tvName)

        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)

        private val tvPhone: TextView = itemView.findViewById(R.id.tvPhone)

        fun bind(user: UsersModel) {

            tvName.text = user.name
            tvEmail.text = user.email
            tvPhone.text = user.phone
        }
    }
}