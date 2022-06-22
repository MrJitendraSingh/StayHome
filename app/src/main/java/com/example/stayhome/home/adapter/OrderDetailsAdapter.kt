package com.example.stayhome.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.databinding.CartOrderViewBinding
import com.example.stayhome.databinding.OrderDetailsItemBinding
import com.example.stayhome.home.dataclass.ViewData

class OrderDetailsAdapter(private var dataList : ArrayList<ViewData>, private val listener: (ViewData) -> Unit) : RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>() {

    inner class ViewHolder( var viewBinding: OrderDetailsItemBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OrderDetailsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = dataList[position]
        var count = 1
        var total = 0
        val t = "OrderAdapter"
        holder.viewBinding.orderSimpleDraweeView.setImageResource(dataListItem.image)
        holder.viewBinding.tvTitle.text = dataListItem.title
//        holder.viewBinding.tvShopName.text = dataListItem.location
        holder.viewBinding.tvPrice.text = dataListItem.price + "/-"
        holder.viewBinding.tvTotalPrice.text = dataListItem.price + "/-"

        holder.viewBinding.orderDetailsItemContainer.setOnClickListener{

//            dataListItem.index = count
//            if (holder.viewBinding.orderItemContainer.isSelected) {
//                listener(dataListItem)

            Log.e("tag", dataListItem.indexs.toString())
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

