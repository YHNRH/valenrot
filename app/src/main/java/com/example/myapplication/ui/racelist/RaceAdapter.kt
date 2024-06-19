package com.example.myapplication.ui.racelist

import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.myapplication.R
    import com.example.myapplication.core.room.entity.Race

class RaceAdapter(
    val context: Context,
    private val raceClickDeleteInterface: RaceClickDeleteInterface,
    private val raceClickInterface: RaceClickInterface
    ) :
        RecyclerView.Adapter<RaceAdapter.ViewHolder>() {

        private val allRaces = ArrayList<Race>()

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTV = itemView.findViewById<TextView>(R.id.name)
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
            holder.nameTV.setText(allRaces[position].name)
            holder.deleteIV.setOnClickListener {
                raceClickDeleteInterface.onDeleteIconClick(allRaces[position])
            }
            holder.itemView.setOnClickListener {
                raceClickInterface.onRaceClick(allRaces[position])
            }
        }

        override fun getItemCount(): Int {
            return allRaces.size
        }

        fun updateList(newList: List<Race>) {
            allRaces.clear()
            allRaces.addAll(newList)
            notifyDataSetChanged()
        }
    }

    interface RaceClickDeleteInterface {
        fun onDeleteIconClick(race: Race)
    }

    interface RaceClickInterface {
        fun onRaceClick(race: Race)
    }
