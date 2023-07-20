package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Doctor
import com.example.healthcaretesting.viewmodel.DoctorListViewModel

class DoctorListFragment : Fragment() {

    private lateinit var viewModel: DoctorListViewModel
    private val adapter = DoctorListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Show bottom nav, drawer and toolbar
        (activity as MainActivity).setComponentsVisibility(true)


        //Change text in toolbar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Doctor List"

        viewModel = ViewModelProvider(this).get(DoctorListViewModel::class.java)

        viewModel.refresh()
        val recViewDoctor = view.findViewById<RecyclerView>(R.id.recViewDoctorList)
        recViewDoctor.layoutManager = LinearLayoutManager(context)
        recViewDoctor.adapter = adapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.doctorLiveData.observe(viewLifecycleOwner, Observer{
            adapter.updateDoctorList(it as ArrayList<Doctor>)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtDoctorListEmpty)

            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility =View.GONE
            }
        })
    }

}