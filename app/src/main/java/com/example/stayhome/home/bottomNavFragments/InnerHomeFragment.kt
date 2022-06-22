package com.example.stayhome.home.bottomNavFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentInnerHomeBinding
import com.example.stayhome.home.adapter.CategoryAdapter
import com.example.stayhome.home.adapter.ItemAdapter
import com.example.stayhome.home.dataclass.CategoryData
import com.example.stayhome.home.dataclass.ViewData

class InnerHomeFragment : Fragment() {

    val t = "InnerHomeFragment"
    lateinit var innerHomeBinding: FragmentInnerHomeBinding
    var dataList : ArrayList<ViewData> = ArrayList()
    var categoryList: ArrayList<CategoryData> = ArrayList()
    var selectedList : ArrayList<ViewData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        innerHomeBinding = FragmentInnerHomeBinding.inflate(inflater, container, false)

//        for (i in 0..20) {
//            val data = ViewData()
//            data.title = "Fast Food"
//            data.price = "99/-"
//            data.image = R.drawable.category1
//            data.location = "Switzerland"
//            data.index = i.toInt()
//            Log.e("taag", i.toString())
//            dataList.add(data)
//        }
        dataList.clear()
        categoryList.clear()

        dataList.add(ViewData(R.drawable.item6, "Samosa", "ITI", "15/-", 1))
        dataList.add(ViewData(R.drawable.item4, "Full Pack", "Pardeshipura", "259/-", 2))
        dataList.add(ViewData(R.drawable.item7, "Chicken", "LIG Road", "90/-", 3))
        dataList.add(ViewData(R.drawable.item5, "Burger", "VijayNagar", "49/-", 4))
        dataList.add(ViewData(R.drawable.item3, "Full Pack", "BhawarKuan", "395/-", 5))
        dataList.add(ViewData(R.drawable.item2, "Masala", "Atal Dawar", "29/-", 6))
        dataList.add(ViewData(R.drawable.item1, "Coconut", "ITI", "15/-", 7))
        dataList.add(ViewData(R.drawable.item8, "Basmati Rice", "ITI", "93/-", 8))
        dataList.add(ViewData(R.drawable.item9, "Dragon Fruit", "Keshav Nagar", "335/-", 9))
        dataList.add(ViewData(R.drawable.item11, "Strawberry", "VijayNagar", "45/-", 10))
        dataList.add(ViewData(R.drawable.item12, "Brinjal", "ITI", "20/-", 11))
        dataList.add(ViewData(R.drawable.item13, "Fish", "ITI", "180/-", 12))
        dataList.add(ViewData(R.drawable.item14, "Broccoli", "ITI", "50/-", 13))
        dataList.add(ViewData(R.drawable.item15, "Carrot", "VijayNagar", "30/-", 14))
        dataList.add(ViewData(R.drawable.item16, "Pineapple", "VijayNagar", "109/-", 15))
        dataList.add(ViewData(R.drawable.item18, "Cabbage", "Pardeshipura", "19/-", 15))
        dataList.add(ViewData(R.drawable.item19, "Capsicum", "Keshav Nagar", "29/-", 15))


        categoryList.add(CategoryData(R.drawable.category1, "Food"))
        categoryList.add(CategoryData(R.drawable.category2,"NonVag"))
        categoryList.add(CategoryData(R.drawable.category4, "Fruits"))
        categoryList.add(CategoryData(R.drawable.category5,"Grocery"))
        categoryList.add(CategoryData(R.drawable.category6,"Raw Food"))
        categoryList.add(CategoryData(R.drawable.category7,"Vegetables"))


        val adapter = ItemAdapter(dataList){ state ->

//            Toast.makeText(requireContext(), "${state.index}", Toast.LENGTH_SHORT).show()
//            val fragment = ResultFragment()
//            val support = requireActivity().supportFragmentManager.beginTransaction()
//            val bundle = Bundle()
//            bundle.putString("index", state.index.toString())
//            bundle.putString("title", state.title)
//            bundle.putString("location", state.location)
//            bundle.putString("calendar", state.calendar)
//            fragment.arguments = bundle
//            support.replace(R.id.fragmentContainerView, fragment).commit()

            selectedList.clear()
            selectedList.add(state)
            val bundle = bundleOf("data" to selectedList)

            findNavController().navigate(R.id.itemDetailsFragment, bundle)

        }
        val manager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
        innerHomeBinding.itemRecyclerView.layoutManager = manager
        innerHomeBinding.itemRecyclerView.adapter = adapter

        val categoryAdapter = CategoryAdapter(categoryList){ state ->

//            Toast.makeText(requireContext(), state.name, Toast.LENGTH_SHORT).show()
//            val fragment = CategoryItemsFragment()
//            val support = requireActivity().supportFragmentManager.beginTransaction()
//            val bundle = Bundle()
//            bundle.putString("title", state.name)
//            Log.e(t,state.name)
//            fragment.arguments = bundle
//            support.replace(R.id.fragmentContainerView2, fragment).commit()

            val bundle = bundleOf("name" to state.name)
            findNavController().navigate(R.id.action_innerHomeFragment_to_categoryItemsFragment, bundle)

        }
        val itemCategory: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        innerHomeBinding.itemCategoryRecyclerView.layoutManager = itemCategory
        innerHomeBinding.itemCategoryRecyclerView.adapter = categoryAdapter


        return innerHomeBinding.root
    }
}