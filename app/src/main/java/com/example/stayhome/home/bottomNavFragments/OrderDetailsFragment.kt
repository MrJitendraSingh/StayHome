package com.example.stayhome.home.bottomNavFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentOrderDetailsBinding
import com.example.stayhome.home.adapter.OrderDetailsAdapter
import com.example.stayhome.home.dataclass.ViewData


class OrderDetailsFragment : Fragment() {
    lateinit var detailsBinding: FragmentOrderDetailsBinding
    var orderList : ArrayList<ViewData> = ArrayList()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        detailsBinding = FragmentOrderDetailsBinding.inflate(inflater, container, false)

        orderList.add(ViewData(R.drawable.item7, "Chicken", "LIG Road", "90", 3))
        orderList.add(ViewData(R.drawable.item5, "Burger", "VijayNagar", "49", 4))
        orderList.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395", 5))

        var total = 0
        for (i in orderList){
            total += i.price.toInt()
        }

        detailsBinding.tvTotalPrice.text = "$total /-"

        val adapter = OrderDetailsAdapter(orderList){}
        detailsBinding.orderDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        detailsBinding.orderDetailsRecyclerView.adapter = adapter


        return detailsBinding.root
    }
}