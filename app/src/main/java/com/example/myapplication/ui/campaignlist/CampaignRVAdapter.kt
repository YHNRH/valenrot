package com.example.myapplication.ui.campaignlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.viewmodel.CharacterViewModel

class CampaignRVAdapter(
    val context: Context,
    private val characterViewModel : CharacterViewModel,
    private val activity : Fragment,
    private val campaignClickDeleteInterface: CampaignClickDeleteInterface,
    private val campaignClickInterface: CampaignClickInterface
    ) :
        RecyclerView.Adapter<CampaignRVAdapter.ViewHolder>(), CharacterClickDeleteInterface{

        private val allCampaigns = ArrayList<Campaign>()
        private lateinit var characterRV: RecyclerView


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val noteTV = itemView.findViewById<TextView>(R.id.name)
            val deleteIV = itemView.findViewById<TextView>(R.id.delete)
            val expandBtn = itemView.findViewById<TextView>(R.id.expand)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.campaign_list_item,
                parent, false
            )

            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            characterRV = holder.itemView.findViewById(R.id.list)
            characterRV.layoutManager = LinearLayoutManager(context)
            val characterRVAdapter = CharacterRVAdapter(context, characterRV,this)

            characterRV.adapter = characterRVAdapter
            characterViewModel.allCharacters.observe(activity) { list ->
                list?.let {
                    characterRVAdapter.updateList(it, allCampaigns[position])
                }
            }

            holder.noteTV.text = allCampaigns[position].name
            holder.deleteIV.setOnClickListener {
                campaignClickDeleteInterface.onDeleteIconClick(allCampaigns[position])
            }
            holder.itemView.setOnClickListener {
                campaignClickInterface.onCampaignClick(allCampaigns[position])
            }


            characterRV.visibility = if (allCampaigns[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

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

    override fun onDeleteIconClick(character: Character) {
        characterViewModel.deleteCharacter(character)
        Toast.makeText(context, "${character.name} Deleted", Toast.LENGTH_LONG).show()
    }
}

    interface CampaignClickDeleteInterface {
        fun onDeleteIconClick(campaign: Campaign)
    }

    interface CampaignClickInterface {
        fun onCampaignClick(campaign: Campaign)
    }
