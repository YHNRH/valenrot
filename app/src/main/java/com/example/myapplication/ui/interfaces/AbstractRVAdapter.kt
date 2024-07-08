package com.example.myapplication.ui.interfaces

import androidx.recyclerview.widget.RecyclerView

abstract class AbstractRVAdapter<T> : RecyclerView.Adapter<AbstractViewHolder>(){
    val allEntities = ArrayList<T>()
    override fun getItemCount(): Int {
        return allEntities.size
    }
}