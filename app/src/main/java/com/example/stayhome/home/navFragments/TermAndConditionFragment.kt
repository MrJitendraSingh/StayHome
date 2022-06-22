package com.example.stayhome.home.navFragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.stayhome.databinding.FragmentTermAndConditionBinding
import com.example.stayhome.retrofit.MoviesAdapter
import com.example.stayhome.retrofit.PopularMovies
import com.example.stayhome.retrofit.ServiceBuilder
import com.example.stayhome.retrofit.TmdbEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TermAndConditionFragment : Fragment() {

    lateinit var binding: FragmentTermAndConditionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTermAndConditionBinding.inflate(inflater, container, false)

//        binding.demoText.text = getString(R.string.demo_text)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies("Movies")

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    binding.recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}