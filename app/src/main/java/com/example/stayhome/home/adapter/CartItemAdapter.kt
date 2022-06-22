package com.example.stayhome.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.R
import com.example.stayhome.databinding.CartOrderViewBinding
import com.example.stayhome.home.dataclass.ViewData

class CartItemAdapter(private val listener: (ViewData) -> Unit) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    var cartList : MutableList<ViewData> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    inner class ViewHolder( var viewBinding: CartOrderViewBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartOrderViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataListItem = cartList[position]
        var count = 1
        var total = 0
        val t = "OrderAdapter"
        holder.viewBinding.orderSimpleDraweeView.setImageResource(dataListItem.image)
        holder.viewBinding.tvTitle.text = dataListItem.title
//        holder.viewBinding.tvShopName.text = dataListItem.location
        holder.viewBinding.tvPrice.text = "${dataListItem.price} /-"
        holder.viewBinding.tvTotalPrice.text = dataListItem.price + "/-"
        holder.viewBinding.btnAdd.setOnClickListener {
            count +=1
            holder.viewBinding.btnRemove.setImageResource(R.drawable.ic_remove)
            holder.viewBinding.tvItemCount.text = count.toString()
            holder.viewBinding.tvTotalPrice.text = (dataListItem.price.toInt() * count).toString()+ "/-"

            dataListItem.indexs = (dataListItem.price.toInt())
            Log.e(t, dataListItem.indexs.toString())
            listener(dataListItem)
        }
        holder.viewBinding.btnRemove.setOnClickListener {
            if (count > 1){
                count -=1
                if (count == 1){
                    holder.viewBinding.btnRemove.setImageResource(R.drawable.ic_baseline_delete_forever_24)
                }else {
                    holder.viewBinding.btnRemove.setImageResource(R.drawable.ic_remove)
                }
                holder.viewBinding.tvItemCount.text = count.toString()
                holder.viewBinding.tvTotalPrice.text = (dataListItem.price.toInt() * count).toString() + "/-"
                dataListItem.indexs = -(dataListItem.price.toInt())
                Log.e(t, dataListItem.indexs.toString())
                listener(dataListItem)
            }else{
                holder.viewBinding.tvItemCount.text = count.toString()
                holder.viewBinding.tvTotalPrice.text = (dataListItem.price.toInt() * count).toString()
                dataListItem.indexs = -(dataListItem.price.toInt())
                dataListItem.state = 0
                Log.e(t, dataListItem.indexs.toString())
                listener(dataListItem)
            }
        }

        holder.viewBinding.orderItemContainer.setOnClickListener{

//            dataListItem.index = count
//            if (holder.viewBinding.orderItemContainer.isSelected) {
//                listener(dataListItem)

            Log.e("tag", dataListItem.indexs.toString())
        }

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}

