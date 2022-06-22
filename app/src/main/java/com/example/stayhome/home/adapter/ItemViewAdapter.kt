package com.example.stayhome.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.R
import com.example.stayhome.databinding.ItemViewBinding
import com.example.stayhome.home.dataclass.ViewData
class ItemViewAdapter(private var dataList : ArrayList<ViewData>, private val listener: (ViewData) -> Unit) : RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {

    inner class ViewHolder(val viewBinding: ItemViewBinding): RecyclerView.ViewHolder(viewBinding.root)
    fun filterList(filterList: ArrayList<ViewData>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = dataList[position]
        holder.viewBinding.simpleDraweeView.setImageResource(dataListItem.image)
        holder.viewBinding.tvTitle.text = dataListItem.title
        holder.viewBinding.tvShopName.text = dataListItem.location
        holder.viewBinding.tvPrice.text = dataListItem.price + "/-"
        holder.viewBinding.itemViewContainer.setOnClickListener{
            dataListItem.st = !dataListItem.st

            notifyDataSetChanged()

            listener(dataListItem)
//            Log.e("tag", dataListItem.title.toString())
        }
        if (dataListItem.st){
            val x = holder.viewBinding.itemViewContainer.setCardBackgroundColor(Color.parseColor("#E679CFDA"))
            Log.e("tag", dataListItem.title.toString())
        }else{
            holder.viewBinding.itemViewContainer.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

//    override fun getFilter(): android.widget.Filter? {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//
//            }
//
//        }
//    }
}

