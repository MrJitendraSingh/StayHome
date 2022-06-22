package com.example.stayhome.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.databinding.CategoryItemBinding
import com.example.stayhome.home.dataclass.CategoryData

class CategoryAdapter(private var dataList : ArrayList<CategoryData>, private val listener: (CategoryData) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder( var viewBinding: CategoryItemBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = dataList[position]
        holder.viewBinding.categoryImageView.setImageResource(dataListItem.image!!)
        holder.viewBinding.tvCategoryName.text = dataListItem.name
        holder.viewBinding.categoryItemContainer.setOnClickListener{
            listener(dataListItem)
            Log.e("tag", dataListItem.index.toString())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}