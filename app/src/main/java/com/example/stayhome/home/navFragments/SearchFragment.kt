package com.example.stayhome.home.navFragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentSearchBinding
import com.example.stayhome.home.adapter.ItemViewAdapter
import com.example.stayhome.home.dataclass.ViewData


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requireActivity().supportFragmentManager.popBackStack()
//        setHasOptionsMenu(true)
    }
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    lateinit var binding: FragmentSearchBinding
    var dataList : ArrayList<ViewData> = ArrayList()
    lateinit var searchView: SearchView
    var itemList : ArrayList<ViewData> = ArrayList()
    lateinit var itemData : String
    lateinit var adapter: ItemViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchView = binding.searchView
//        try {
//            itemData = requireArguments().getString("data") as String
//            Log.e("tag",itemData)
//        }catch (e : Exception){
//            return null
//        }

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


        adapter = ItemViewAdapter(dataList){}
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        binding.searchView.onActionViewExpanded()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchText: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText : String): Boolean {
                filter(newText)
                return false
            }
        })

        return binding.root
    }

    private fun filter(text: String) {
        val filterList: ArrayList<ViewData> = ArrayList()
        for (item in dataList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.title.toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item)
            }
        }
        if (filterList.isEmpty()) {
//            Toast.makeText(
//                requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filterList)
        }
    }

}