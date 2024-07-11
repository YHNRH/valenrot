package com.example.myapplication.ui.interfaces

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

abstract class AbstractViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val nameTV: TextView = itemView.findViewById(R.id.name)
    val deleteIV: ImageView = itemView.findViewById(R.id.delete)
    val expandBtn: ImageView = itemView.findViewById(R.id.expand)
    val addBtn: ImageView = itemView.findViewById(R.id.addBtn)
}