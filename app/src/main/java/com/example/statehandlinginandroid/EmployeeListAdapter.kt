package com.example.statehandlinginandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.statehandlinginandroid.apiresponse.Data
import kotlinx.android.synthetic.main.child_layout.view.*

class EmployeeListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: ArrayList<Data> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.child_layout, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EmployeeViewHolder).bind()
    }



    inner class EmployeeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            if (list.isNotEmpty()) {
                setViews()
            }
        }

        private fun setViews() {
            view.apply {
                name.text = list[adapterPosition].employeeName
                age.text = "age : ${list[adapterPosition].employeeAge}"
                salary.text ="₹${list[adapterPosition].employeeSalary}"
            }
        }


    }

}
