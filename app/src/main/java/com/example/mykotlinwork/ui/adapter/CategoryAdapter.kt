package com.example.mykotlinwork.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinwork.R
import com.example.mykotlinwork.ui.models.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories = mutableListOf<Category>()

    class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val categoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int) {
        holder.categoryName.text = categories[position].category_name
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateList(newList: List<Category>) {
        categories.clear()
        categories.addAll(newList)
        notifyDataSetChanged()
    }
}