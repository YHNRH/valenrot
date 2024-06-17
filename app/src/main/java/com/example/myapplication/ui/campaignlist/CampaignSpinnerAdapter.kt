package com.example.myapplication.ui.campaignlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign

class CampaignSpinnerAdapter(
    context: Context
    ) : ArrayAdapter<Campaign>(
        context,
        ViewHolder.LAYOUT) {
        private val allCampaigns = ArrayList<Campaign>()
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
            itemView.findViewById<TextView>(R.id.name).text = model.name
            return itemView
        }

    override fun getCount(): Int {
        return allCampaigns.size
    }

    override fun getItem(position: Int): Campaign {
        return allCampaigns[position]
    }

        private class ViewHolder {
            companion object {
                @LayoutRes
                val LAYOUT = R.layout.list_item
            }
        }

    fun updateList(newList: List<Campaign>) {
        allCampaigns.clear()
        allCampaigns.addAll(newList)
        notifyDataSetChanged()
    }
}