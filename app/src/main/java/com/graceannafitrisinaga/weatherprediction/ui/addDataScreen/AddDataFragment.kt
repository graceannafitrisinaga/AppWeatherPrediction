package com.graceannafitrisinaga.weatherprediction.ui.addDataScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.graceannafitrisinaga.weatherprediction.R
import com.graceannafitrisinaga.weatherprediction.database.DataItem
import com.graceannafitrisinaga.weatherprediction.databinding.FragmentAddDataBinding

class AddDataFragment : Fragment() {

    companion object {
        fun newInstance() = AddDataFragment()
    }

    lateinit var binding: FragmentAddDataBinding
    private lateinit var viewModel: AddDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDataBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[AddDataViewModel::class.java]


        // On student data submit
        binding.submitButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val roll = binding.rollNumberEditText.text.toString()
            val reg = binding.registrationNumberEditText.text.toString()
            val branch = binding.branchEditText.text.toString()
            val year = binding.yearEditText.text.toString()

            if (roll != "" && firstName != "" && branch != ""){
                val student = DataItem(0, firstName, lastName, email, roll, reg, branch, year)
                viewModel.addData(requireContext(), student)
                Toast.makeText(this.context, "Added", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.context, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }

}