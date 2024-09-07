package com.example.getmethod.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getmethod.R
import com.example.getmethod.model.MyDataItem

class MyAdapter(private val context: Activity, private val postArrayList: List<MyDataItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.id)
        var title: TextView = itemView.findViewById(R.id.title)
        var body: TextView = itemView.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = postArrayList[position]
        holder.id.text = "Id : " + currItem.id.toString()
        holder.title.text = "Title : " + currItem.title
        holder.body.text = "Body : " + currItem.body
    }
}