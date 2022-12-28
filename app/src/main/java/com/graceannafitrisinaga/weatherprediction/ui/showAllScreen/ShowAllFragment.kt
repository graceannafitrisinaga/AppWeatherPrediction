package com.graceannafitrisinaga.weatherprediction.ui.showAllScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.graceannafitrisinaga.weatherprediction.R
import com.graceannafitrisinaga.weatherprediction.databinding.FragmentShowAllBinding
import com.graceannafitrisinaga.weatherprediction.rv.ShowAllDataRecyclerViewAdapter

class ShowAllFragment : Fragment() {

    companion object {
        fun newInstance() = ShowAllFragment()
    }

    private lateinit var binding: FragmentShowAllBinding
    private lateinit var viewModel: ShowAllViewModel
    private lateinit var adapter: ShowAllDataRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAllBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[ShowAllViewModel::class.java]

        binding.showAllDataRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = ShowAllDataRecyclerViewAdapter(this.requireContext(), viewModel)
        binding.showAllDataRecyclerView.adapter = this.adapter

        viewModel.getAllData(this.requireContext()).observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        return binding.root
    }

}