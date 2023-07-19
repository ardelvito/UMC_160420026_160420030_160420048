package com.example.healthcaretesting.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.model.Facility
import com.example.healthcaretesting.viewmodel.FacilityListViewModel

class FacilityListFragment : Fragment() {

    private lateinit var viewModel: FacilityListViewModel
    private val facilityAdapter = FacilityListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facility_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setComponentsVisibility(true)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Facility List"

        val sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)

        viewModel = ViewModelProvider(this)[FacilityListViewModel::class.java]
        viewModel.refresh()
        val recViewFacility = view.findViewById<RecyclerView>(R.id.recViewFacility)
        recViewFacility.layoutManager = LinearLayoutManager(context)
        recViewFacility.adapter = facilityAdapter
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.facilityLiveData.observe(viewLifecycleOwner) {
            facilityAdapter.updateFacilityList(it as ArrayList<Facility> /* = java.util.ArrayList<com.example.todoapp.model.Model.Todo> */)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtFacilityListEmpty)
            if (it.isEmpty()) {
                txtEmpty?.visibility = View.VISIBLE
            } else {
                txtEmpty?.visibility = View.GONE
            }
        }
    }


    private fun isLoggedIn(sharedPreferences: SharedPreferences): Boolean {
        // Retrieve the login state from SharedPreferences
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

}