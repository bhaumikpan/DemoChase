package com.example.featureui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.featuredomian.models.School
import com.example.featureui.R

class CustomAdapter (private var mList: List<School>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]
        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.school_name



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataList(data: List<School>) {
        mList = data
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                if (bindingAdapterPosition >= 0) {
                    itemClickListener.onItemClick(bindingAdapterPosition)
                }
            }
        }

    }

}