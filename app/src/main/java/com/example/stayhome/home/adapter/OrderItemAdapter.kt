package com.example.stayhome.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.databinding.CartOrderViewBinding
import com.example.stayhome.databinding.OrderItemViewBinding
import com.example.stayhome.home.dataclass.ShopData
import com.example.stayhome.home.dataclass.ViewData

class OrderItemAdapter(private var dataList : ArrayList<ShopData>, private val listener: (Boolean) -> Unit) : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    inner class ViewHolder( var viewBinding: OrderItemViewBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OrderItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = dataList[position]
        val t = "OrderAdapter"
        holder.viewBinding.tvShopName.text = dataListItem.name
        holder.viewBinding.tvTotalPrice.text = dataListItem.total
        holder.viewBinding.tvDate.text = dataListItem.date
        holder.viewBinding.tvTime.text = dataListItem.time

        holder.viewBinding.orderItemContainer.setOnClickListener{

//            dataListItem.index = count
//            if (holder.viewBinding.orderItemContainer.isSelected) {
                listener(true)

//            Log.e(t, dataListItem.name)
//            Log.e(t, holder.viewBinding.orderItemContainer.isSelected.toString())
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

