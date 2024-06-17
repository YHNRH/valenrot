package com.example.myapplication.ui.campaignlist

import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
    import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character

class CharacterRVAdapter(
    val context: Context,
    val parent: RecyclerView,
    private val characterClickDeleteInterface: CharacterClickDeleteInterface
) :
        RecyclerView.Adapter<CharacterRVAdapter.ViewHolder>() {

        private val allCharacter = ArrayList<Character>()
        var expand = false
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val noteTV = itemView.findViewById<TextView>(R.id.name)
            val dateTV = itemView.findViewById<TextView>(R.id.health)
            val deleteIV = itemView.findViewById<TextView>(R.id.delete)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.character_list_item,
                parent, false
            )
            return ViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.noteTV.setText(allCharacter[position].name)
            holder.dateTV.setText("Last Updated : " + allCharacter.get(position).lastChangeDate)
            holder.deleteIV.setOnClickListener {
                characterClickDeleteInterface.onDeleteIconClick(allCharacter[position])
            }
            holder.itemView.setOnClickListener {
                //campaignClickInterface.onCampaignClick(allCampaigns[position])
            }
        }

        override fun getItemCount(): Int {
            return allCharacter.size
        }

        fun updateList(newList: List<Character>, campaign: Campaign) {
            allCharacter.clear()
            val test = newList.filter { it.campaignId == campaign.uid}
            allCharacter.addAll(test)
            notifyDataSetChanged()
        }
    }

interface CharacterClickDeleteInterface {
    fun onDeleteIconClick(character: Character)
}

