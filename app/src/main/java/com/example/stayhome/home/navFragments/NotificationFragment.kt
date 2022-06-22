package com.example.stayhome.home.navFragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentNotificationBinding
import com.example.stayhome.home.adapter.ItemViewAdapter
import com.example.stayhome.home.dataclass.ViewData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NotificationFragment : Fragment() {

    lateinit var notificationBinding: FragmentNotificationBinding
    var notificationList : ArrayList<ViewData> = ArrayList()
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        notificationBinding = FragmentNotificationBinding.inflate(inflater, container, false)

        val currentDate = Calendar.getInstance()
        val simpleFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm aa")
        val new = currentDate.timeZone
        Log.e("tag", new.toString())
        val t = simpleFormatter.format(currentDate.time)

        notificationList.add(ViewData(R.drawable.item4, "Contrary to popular belief, Lorem Ipsum is not simply random text.", t.toString(), "259/-", 2))
        notificationList.add(ViewData(R.drawable.item7, " It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old", t.toString(), "90/-", 3))
        notificationList.add(ViewData(R.drawable.item5, "Burger", t.toString(), "49/-", 4))
        notificationList.add(ViewData(R.drawable.item3, "Full Pack", t.toString(), "395/-", 5))


        val adapter = ItemViewAdapter(notificationList){ state->

        }
        notificationBinding.notificationRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationBinding.notificationRecyclerView.adapter = adapter

        return notificationBinding.root
    }
}