package com.example.stayhome.home.bottomNavFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentOrderItemBinding
import com.example.stayhome.home.adapter.ItemViewAdapter
import com.example.stayhome.home.adapter.OrderItemAdapter
import com.example.stayhome.home.dataclass.ShopData
import com.example.stayhome.home.dataclass.ViewData

class OrderItemFragment : Fragment() {
    val t = "OrderFragment"
    var orderList : ArrayList<ShopData> = ArrayList()
    var foodList : ArrayList<ViewData> = ArrayList()
    lateinit var orderBinding: FragmentOrderItemBinding
    var state : Boolean = false
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        orderBinding = FragmentOrderItemBinding.inflate(inflater, container, false)

        orderList.add(ShopData("Gupta ji ki Dukan", "1440", "01/05/2022", "11:30 AM"))
        orderList.add(ShopData("Gupta ji ki Dukan", "1440", "01/05/2022", "11:30 AM"))
        orderList.add(ShopData("Gupta ji ki Dukan", "1440", "01/05/2022", "11:30 AM"))
        orderList.add(ShopData("Gupta ji ki Dukan", "1440", "01/05/2022", "11:30 AM"))
        orderList.add(ShopData("Gupta ji ki Dukan", "1440", "01/05/2022", "11:30 AM"))

        foodList.add(ViewData(R.drawable.grocery1, "Maggi", "LIG Road", "20/-", 3))
        foodList.add(ViewData(R.drawable.grocery2, "Fartune Oil", "BhawarKuan", "395/-", 5))
        foodList.add(ViewData(R.drawable.grocery3, "Honey", "ITI", "180/-", 12))
        foodList.add(ViewData(R.drawable.grocery4, "Milk", "ITI", "60/-", 12))
        foodList.add(ViewData(R.drawable.grocery3, "Honey", "BhawarKuan", "360/-", 12))

        val adapter = OrderItemAdapter(orderList) { clecked ->

//                val host = NavHostFragment.create(R.navigation.bottom_navigation)
//            val host = NavHostFragment.create(R.navigation.bottom_navigation)
//            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.mainActivityFragmentContainer,host).setPrimaryNavigationFragment(host).commit()

            findNavController().navigate(R.id.action_historyFragment_to_orderDetailsFragment)
        }
        orderBinding.orderRecyclerView.layoutManager = LinearLayoutManager(context)
        orderBinding.orderRecyclerView.adapter = adapter

        return orderBinding.root
    }
}