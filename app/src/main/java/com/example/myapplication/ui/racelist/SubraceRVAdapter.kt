package com.example.myapplication.ui.racelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.Subrace

class SubraceRVAdapter(
    val context: RaceAdapter
) :
        RecyclerView.Adapter<SubraceRVAdapter.ViewHolder>() {

        private val allSubrace = ArrayList<Subrace>()
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val infoTV = itemView.findViewById<TextView>(R.id.info)
            val deleteBtn = itemView.findViewById<ImageView>(R.id.delete)
            val addBtn = itemView.findViewById<TextView>(R.id.addBtn)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_character,
                parent, false
            )

            return ViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.infoTV.setText(allSubrace[position].title + ", " )
            holder.deleteBtn.setOnClickListener {
                //characterClickDeleteInterface.onDeleteClick(allCharacter[position].character)
            }
            holder.itemView.setOnClickListener {
                context.onClick(allSubrace[position])
            }

        }

        override fun getItemCount(): Int {
            return allSubrace.size
        }

        fun updateList(newList: List<Subrace>, race: Race) {
            allSubrace.clear()
            val test = newList.filter { it.raceId == race.uid}
            allSubrace.addAll(test)
            notifyDataSetChanged()
        }
    }

