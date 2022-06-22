package com.example.stayhome.home.dataclass

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedPreference(context: Context) {

    private val sharedPre : SharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
    private val sharedPref : SharedPreferences.Editor = sharedPre.edit()
    fun setString(key: String, value:String){
        sharedPref.putString(key,value)
        sharedPref.apply()
    }
    fun getString(key: String) : String?{
        return sharedPre.getString(key,null)
    }

    fun saveData(key: String, arrayList: ArrayList<ViewData>) {
        val gson = Gson()
        val json = gson.toJson(arrayList)
        sharedPref.putString(key, json)
        sharedPref.apply()
    }
    fun saveArrayList(key: String?, list: ArrayList<ViewData>) {
        val gson = Gson()
        val json = gson.toJson(list)
        sharedPref.putString(key, json)
        sharedPref.apply() // This line is IMPORTANT !!!
    }
    fun getArrayList(key: String?): ArrayList<ViewData> {
        val gson = Gson()
        val json = sharedPre.getString(key, null)
        val type = object : TypeToken<ArrayList<ViewData?>?>() {}.type
        return gson.fromJson(json, type)
    }

    fun allClear(){
        val sharedPref : SharedPreferences.Editor = sharedPre.edit()
        sharedPref.clear()
        sharedPref.apply()
        return
    }
}