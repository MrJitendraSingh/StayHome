package com.example.stayhome.home.bottomNavFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentCartBinding
import com.example.stayhome.home.adapter.CartItemAdapter
import com.example.stayhome.home.dataclass.SharedPreference
import com.example.stayhome.home.dataclass.ViewData

class CartFragment : Fragment() {

    private lateinit var adapter: CartItemAdapter
    val t = "CartFragment"
    lateinit var cartBinding: FragmentCartBinding
//    var cartList : MutableList<ViewData> = ArrayList()
    private var itemData : ArrayList<ViewData> = ArrayList()
    var grandTotal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        cartBinding = FragmentCartBinding.inflate(inflater, container, false)
        val sharedP = SharedPreference(requireContext())
        try {
            itemData = sharedP.getArrayList("data")
        }catch (e : Exception){
            Toast.makeText(requireContext(), "Data not found !", Toast.LENGTH_SHORT).show()
        }

//        cartList.add(ViewData(R.drawable.item7, "Chicken", "LIG Road", "90", 3))
//        cartList.add(ViewData(R.drawable.item5, "Burger", "VijayNagar", "49", 4))
//        cartList.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395", 5))

        var total = 0
        for (i in itemData){
            total += i.price.toInt()
        }
        grandTotal = total
        cartBinding.totalPrice.text = grandTotal.toString()

        adapter = CartItemAdapter{ state ->
            val s = itemData.indexOf(state)
            Log.e(t, s.toString())
            grandTotal += state.indexs
            if (state.state == 0){
                Log.e(t, state.indexs.toString())
                itemData.removeAt(s)
                removeItemFromAdapter(s)
                cartBinding.totalPrice.text = grandTotal.toString()
            }else {
                cartBinding.totalPrice.text = grandTotal.toString()
            }
//            Toast.makeText(requireContext(), "${state.index + total}", Toast.LENGTH_SHORT).show()

        }
        adapter.cartList = itemData
        cartBinding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartBinding.cartRecyclerView.adapter = adapter



        return cartBinding.root
    }

    fun removeItemFromAdapter(position: Int){
        adapter.notifyItemRemoved(position)
    }
}