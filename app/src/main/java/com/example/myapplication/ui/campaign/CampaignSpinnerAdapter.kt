package com.example.myapplication.ui.campaign

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign

class CampaignSpinnerAdapter<T>(
    context: Context
    ) : ArrayAdapter<T>(
        context,
    ViewHolder.LAYOUT
) {
        private val allEntities = ArrayList<T>()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return onBindView(parent, position)
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            return onBindView(parent, position)
        }

        private fun onBindView(parent: ViewGroup, position: Int): View {
            val model = getItem(position)
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.spinner_item,
                parent, false
            )
            itemView.findViewById<TextView>(R.id.name).text = model.toString()
            return itemView
        }

    override fun getCount(): Int {
        return allEntities.size
    }

    override fun getItem(position: Int): T {
        return allEntities[position]
    }

        private class ViewHolder {
            companion object {
                @LayoutRes
                val LAYOUT = R.layout.list_item_race
            }
        }

    fun updateList(newList: List<T>) {
        allEntities.clear()
        allEntities.addAll(newList)
        notifyDataSetChanged()
    }
}