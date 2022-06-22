package com.example.stayhome.home.bottomNavFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentItemDetailsBinding
import com.example.stayhome.home.adapter.ItemViewAdapter
import com.example.stayhome.home.dataclass.SharedPreference
import com.example.stayhome.home.dataclass.ViewData
import com.facebook.drawee.backends.pipeline.Fresco

class ItemDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(requireContext());
    }
    lateinit var detailsBinding: FragmentItemDetailsBinding
    var itemData : ArrayList<ViewData> = ArrayList()
    var itemdata : ArrayList<ViewData> = ArrayList()
    var selectedList : ArrayList<ViewData> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        detailsBinding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        val sharedP = SharedPreference(requireContext())
        itemdata.clear()
        detailsBinding.shopImage.setActualImageResource(R.drawable.shopimage)
        detailsBinding.tvShopName.text = "Gupta Ji ki Dukan"
        detailsBinding.tvShopTiming.text = "11AM to 9PM"
        var location: String? = null
        try {
            itemData = requireArguments().getSerializable("data") as ArrayList<ViewData>

            for (i in itemData) {

                itemdata.add(ViewData(i.image, i.title, i.location, i.price, i.indexs))
                location = i.location
            }
        }catch (e:Exception){
            Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show()
        }


        itemdata.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395", 5))
        itemdata.add(ViewData(R.drawable.item2, "Masala", "Atal Dawar", "29", 6))
        itemdata.add(ViewData(R.drawable.item1, "Coconut", "ITI", "15", 7))
        itemdata.add(ViewData(R.drawable.item8, "Basmati Rice", "ITI", "93", 8))
        itemdata.add(ViewData(R.drawable.item9, "Dragon Fruit", "Keshav Nagar", "335", 9))
        itemdata.add(ViewData(R.drawable.item11, "Strawberry", "VijayNagar", "45", 10))
        itemdata.add(ViewData(R.drawable.item12, "Brinjal", "ITI", "20", 11))
        detailsBinding.tvShopAddress.text = location

        val adapter = ItemViewAdapter(itemdata){ state->
            if (state.st) {
                selectedList.add(state)
            }else{
                selectedList.remove(state)
            }
            Toast.makeText(context, state.title, Toast.LENGTH_SHORT).show()
        }
        detailsBinding.shopItemRecyclerView.layoutManager = LinearLayoutManager(context)
        detailsBinding.shopItemRecyclerView.adapter = adapter

        detailsBinding.btnAddToCart.setOnClickListener {
            sharedP.saveArrayList("data", selectedList)
//            val bundle = bundleOf("data" to selectedList)
//            findNavController().navigate(R.id.action_itemDetailsFragment_to_cartFragment, bundle)
        }

        return detailsBinding.root
    }
}