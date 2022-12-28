package com.graceannafitrisinaga.weatherprediction.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.graceannafitrisinaga.weatherprediction.R
import com.graceannafitrisinaga.weatherprediction.database.DataItem
import com.graceannafitrisinaga.weatherprediction.ui.showAllScreen.ShowAllViewModel

class ShowAllDataRecyclerViewAdapter(private val context: Context, private val viewModel: ShowAllViewModel) : RecyclerView.Adapter<ShowAllDataRecyclerViewAdapter.DataViewHolder>() {

    private val allData : ArrayList<DataItem> = ArrayList()

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.userNameTextView)
        val roll : TextView = itemView.findViewById(R.id.rollNumberTextView)
        val registration : TextView = itemView.findViewById(R.id.registrationTextView)
        val email : TextView = itemView.findViewById(R.id.emailTextView)
        val branch : TextView = itemView.findViewById(R.id.branchTextView)
        val year : TextView = itemView.findViewById(R.id.yearTextView)
        val deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewHolder = DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentData = allData[position]

        holder.name.text = "${currentData.firstName} ${currentData.lastName}"
        holder.roll.text = currentData.rollNum
        holder.registration.text = currentData.regNum
        holder.email.text = currentData.email
        holder.branch.text = currentData.branch
        holder.year.text = currentData.year

        holder.deleteBtn.setOnClickListener {
            viewModel.deleteData(context, currentData)
        }
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    fun updateData(list: List<DataItem>){
        allData.clear()
        allData.addAll(list)
        this.notifyDataSetChanged()
    }
}