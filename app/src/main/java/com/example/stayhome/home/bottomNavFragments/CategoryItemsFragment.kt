package com.example.stayhome.home.bottomNavFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentCategoryItemsBinding
import com.example.stayhome.home.adapter.ItemViewAdapter
import com.example.stayhome.home.dataclass.ViewData
import com.facebook.drawee.backends.pipeline.Fresco

class CategoryItemsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(requireContext());
//        requireActivity().supportFragmentManager.popBackStack()
    }


    val t = "CategoryItemsFragment"
    var foodList : ArrayList<ViewData> = ArrayList()
    var nonVagList : ArrayList<ViewData> = ArrayList()
    var fruitsList : ArrayList<ViewData> = ArrayList()
    var groceryList : ArrayList<ViewData> = ArrayList()
    var rawFoodList : ArrayList<ViewData> = ArrayList()
    var vegetablesList : ArrayList<ViewData> = ArrayList()

    lateinit var itemsBinding: FragmentCategoryItemsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        itemsBinding = FragmentCategoryItemsBinding.inflate(inflater, container, false)

//        val categoryName = requireArguments().getString("name") as String
//        Log.e(t,categoryName)
        val name = arguments?.getString("name")
        requireActivity().title = name

        if (name == "Food"){
            foodList.clear()
            foodList.add(ViewData(R.drawable.item6, "Samosa", "ITI", "15", 1))
            foodList.add(ViewData(R.drawable.item4, "Full Pack", "Pardeshipura", "259", 2))
            foodList.add(ViewData(R.drawable.item7, "Chicken", "LIG Road", "90", 3))
            foodList.add(ViewData(R.drawable.item5, "Burger", "VijayNagar", "49", 4))
            foodList.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395", 5))
        }
        else if (name == "NonVag") {
            foodList.clear()
            foodList.add(ViewData(R.drawable.item7, "Chicken", "LIG Road", "90", 3))
            foodList.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395", 5))
            foodList.add(ViewData(R.drawable.item13, "Fish", "ITI", "180", 12))
        }
        else if (name == "Fruits") {
            foodList.clear()
            foodList.add(ViewData(R.drawable.item1, "Coconut", "ITI", "15", 7))
            foodList.add(ViewData(R.drawable.item9, "Dragon Fruit", "Keshav Nagar", "335", 9))
            foodList.add(ViewData(R.drawable.item11, "Strawberry", "VijayNagar", "45", 10))
            foodList.add(ViewData(R.drawable.item16, "Pineapple", "VijayNagar", "109", 15))
        }
        else if (name == "Vegetables") {
            foodList.clear()
            foodList.add(ViewData(R.drawable.item12, "Brinjal", "ITI", "20", 11))
            foodList.add(ViewData(R.drawable.item13, "Fish", "ITI", "180", 12))
            foodList.add(ViewData(R.drawable.item14, "Broccoli", "ITI", "50", 13))
            foodList.add(ViewData(R.drawable.item15, "Carrot", "VijayNagar", "30", 14))
            foodList.add(ViewData(R.drawable.item18, "Cabbage", "Pardeshipura", "19", 15))
            foodList.add(ViewData(R.drawable.item19, "Capsicum", "Keshav Nagar", "29", 15))
        }
        else if (name == "Raw Food") {
            foodList.clear()
            foodList.add(ViewData(R.drawable.rawfood1, "wheat", "ITI", "21", 7))
            foodList.add(ViewData(R.drawable.rawfood2, "Black gram", "Pardeshipura", "50", 7))
            foodList.add(ViewData(R.drawable.rawfood3, "Green gram", "VijayNagar", "70", 7))
            foodList.add(ViewData(R.drawable.rawfood4, "Red kidney beans", "VijayNagar", "100", 7))
            foodList.add(ViewData(R.drawable.rawfood5, "Soybean", "BhawarKuan", "70", 7))
            foodList.add(ViewData(R.drawable.rawfood9, "Papad", "BhawarKuan", "20", 7))
            foodList.add(ViewData(R.drawable.rawfood6, "Green gram", "BhawarKuan", "85", 7))
            foodList.add(ViewData(R.drawable.rawfood8, "Corn cob", "VijayNagar", "15", 7))
        }
        else if (name == "Grocery") {
            foodList.clear()
            foodList.add(ViewData(R.drawable.grocery1, "Maggi", "LIG Road", "20", 3))
            foodList.add(ViewData(R.drawable.grocery2, "Fartune Oil", "BhawarKuan", "395", 5))
            foodList.add(ViewData(R.drawable.grocery3, "Honey", "ITI", "180", 12))
            foodList.add(ViewData(R.drawable.grocery4, "Milk", "ITI", "60", 12))
            foodList.add(ViewData(R.drawable.grocery3, "Honey", "BhawarKuan", "360", 12))
        }

        val adapter = ItemViewAdapter(foodList){ state->
            groceryList.clear()
            groceryList.add(state)
            val bundle = bundleOf("data" to groceryList)

            findNavController().navigate(R.id.action_categoryItemsFragment_to_itemDetailsFragment, bundle)
        }
        itemsBinding.itemViewRecyclerView.layoutManager = LinearLayoutManager(context)
        itemsBinding.itemViewRecyclerView.adapter = adapter


        return itemsBinding.root
    }
    companion object{
        fun recyclerView(list:ArrayList<ViewData>, recyclerView: RecyclerView, context: Context ){
            val adapter = ItemViewAdapter(list){ state->
                Toast.makeText(context, state.title, Toast.LENGTH_SHORT).show()

//                val bundle = bundleOf("name" to state.name)
//                Navigation.findNavController().navigate(R.id.categoryItemsFragment2, bundle)
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }
}