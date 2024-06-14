package com.example.myapplication.ui.campaignlist

import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race

class CampaignAdapter(
    val context: Context,
    private val raceClickDeleteInterface: RaceClickDeleteInterface,
    private val raceClickInterface: RaceClickInterface
    ) :
        RecyclerView.Adapter<CampaignAdapter.ViewHolder>() {

        private val allCampaigns = ArrayList<Campaign>()

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val noteTV = itemView.findViewById<TextView>(R.id.name)
            val dateTV = itemView.findViewById<TextView>(R.id.health)
            val deleteIV = itemView.findViewById<TextView>(R.id.delete)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent, false
            )
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.noteTV.setText(allCampaigns[position].name)
            holder.dateTV.setText("Last Updated : " + allCampaigns.get(position).name)
            holder.deleteIV.setOnClickListener {
                raceClickDeleteInterface.onDeleteIconClick(allCampaigns[position])
            }
            holder.itemView.setOnClickListener {
                raceClickInterface.onRaceClick(allCampaigns[position])
            }
        }

        override fun getItemCount(): Int {
            return allCampaigns.size
        }

        fun updateList(newList: List<Campaign>) {
            allCampaigns.clear()
            allCampaigns.addAll(newList)
            notifyDataSetChanged()
        }
    }

    interface RaceClickDeleteInterface {
        fun onDeleteIconClick(campaign: Campaign)
    }

    interface RaceClickInterface {
        fun onRaceClick(campaign: Campaign)
    }
