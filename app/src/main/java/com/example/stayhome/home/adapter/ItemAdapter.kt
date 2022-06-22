package com.example.stayhome.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.databinding.ItemCardViewBinding
import com.example.stayhome.home.dataclass.ViewData

class ItemAdapter(private var dataList : ArrayList<ViewData>, private val listener: (ViewData) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder( var viewBinding: ItemCardViewBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = dataList[position]
        holder.viewBinding.imageView.setImageResource(dataListItem.image)
        holder.viewBinding.tvTitle.text = dataListItem.title
        holder.viewBinding.tvShopName.text = dataListItem.location
        holder.viewBinding.tvPrice.text = dataListItem.price
        holder.viewBinding.itemContainer.setOnClickListener{
            listener(dataListItem)
            Log.e("tag", dataListItem.indexs.toString())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

