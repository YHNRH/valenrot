package com.example.myapplication.ui.campaignlist

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.common.flip
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.viewmodel.CharacterViewModel

class CampaignRVAdapter(
    private val activity : CampaignListFragment,
    private val characterViewModel : CharacterViewModel,
    ) :
        RecyclerView.Adapter<CampaignRVAdapter.ViewHolder>(){

        private val allCampaigns = ArrayList<Campaign>()
        private lateinit var characterRV: RecyclerView


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val noteTV = itemView.findViewById<TextView>(R.id.name)
            val deleteIV = itemView.findViewById<ImageView>(R.id.delete)
            val expandBtn = itemView.findViewById<ImageView>(R.id.expand)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_campaign,
                parent, false
            )

            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            characterRV = holder.itemView.findViewById(R.id.list)
            characterRV.layoutManager = LinearLayoutManager(activity.requireContext())
            val characterRVAdapter = CharacterRVAdapter(activity.requireContext())

            characterRV.adapter = characterRVAdapter
            characterViewModel.allCharacters.observe(activity) { list ->
                list?.let {
                    characterRVAdapter.updateList(it, allCampaigns[position])
                }
            }

            holder.noteTV.text = allCampaigns[position].name
            holder.deleteIV.setOnClickListener {
                activity.onDeleteClick(allCampaigns[position])
            }
            holder.itemView.setOnClickListener {
                activity.onClick(allCampaigns[position])
            }


            characterRV.visibility = if (allCampaigns[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

            if (allCampaigns[position].isExpanded){
                val bitmap = BitmapFactory.decodeResource(
                    activity.resources,
                    R.drawable.expand_icon
                )
                val cx = bitmap.width / 2f
                val cy = bitmap.height / 2f
                val flippedBitmap = bitmap.flip(1f, -1f, cx, cy)
                holder.expandBtn.setImageBitmap(flippedBitmap)
            } else {
                holder.expandBtn.setImageResource(R.drawable.expand_icon)
            }
            holder.expandBtn.setOnClickListener {
                allCampaigns[position].isExpanded = !allCampaigns[position].isExpanded
                notifyDataSetChanged()
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
