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
import com.example.myapplication.core.room.entity.CharacterAndRace

class CharacterRVAdapter(
    val context: Context,
    val parent: RecyclerView,
    private val characterClickDeleteInterface: CharacterClickDeleteInterface
) :
        RecyclerView.Adapter<CharacterRVAdapter.ViewHolder>() {

        private val allCharacter = ArrayList<CharacterAndRace>()
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTV = itemView.findViewById<TextView>(R.id.name)
            val raceTV = itemView.findViewById<TextView>(R.id.race)
            val deleteBtn = itemView.findViewById<TextView>(R.id.delete)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.character_list_item,
                parent, false
            )
            return ViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.nameTV.setText(allCharacter[position].character .name)
            holder.raceTV.setText(allCharacter[position].race.name)
            holder.deleteBtn.setOnClickListener {
                characterClickDeleteInterface.onDeleteIconClick(allCharacter[position].character)
            }
            holder.itemView.setOnClickListener {
                //campaignClickInterface.onCampaignClick(allCampaigns[position])
            }
        }

        override fun getItemCount(): Int {
            return allCharacter.size
        }

        fun updateList(newList: List<CharacterAndRace>, campaign: Campaign) {
            allCharacter.clear()
            val test = newList.filter { it.character.campaignId == campaign.uid}
            allCharacter.addAll(test)
            notifyDataSetChanged()
        }
    }

interface CharacterClickDeleteInterface {
    fun onDeleteIconClick(character: Character)
}

