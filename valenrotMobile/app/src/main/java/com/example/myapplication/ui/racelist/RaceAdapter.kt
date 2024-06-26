package com.example.myapplication.ui.racelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.viewmodel.SubraceViewModel
import android.graphics.BitmapFactory
import android.widget.LinearLayout
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.core.common.flip
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.ui.interfaces.OnClickEntityInterface
import java.text.SimpleDateFormat
import java.util.Date

class RaceAdapter(
    val context: RaceListFragment,
    private val subraceViewModel : SubraceViewModel
    ) :
        RecyclerView.Adapter<RaceAdapter.ViewHolder>(),
        OnClickEntityInterface<Subrace>{

        private val allRaces = ArrayList<Race>()
        private lateinit var subraceRV: RecyclerView

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTV: TextView  = itemView.findViewById(R.id.name)
            val deleteIV: ImageView  = itemView.findViewById(R.id.delete)
            val expandBtn: ImageView  = itemView.findViewById(R.id.expand)
            val addBtn: ImageView = itemView.findViewById(R.id.addBtn)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_race,
                parent, false
            )
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            subraceRV = holder.itemView.findViewById(R.id.list)
            subraceRV.layoutManager = LinearLayoutManager(context.requireContext())
            val subraceRVAdapter = SubraceRVAdapter(this)

            subraceRV.adapter = subraceRVAdapter
            subraceViewModel.allEntities.observe(context) { list ->
                list?.let {
                    subraceRVAdapter.updateList(it, allRaces[position])
                }
            }

            subraceRV.visibility =  if (allRaces[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE
            holder.addBtn.visibility = if (allRaces[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

            if (allRaces[position].isExpanded){
                val bitmap = BitmapFactory.decodeResource(
                    context.resources,
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
                allRaces[position].isExpanded = !allRaces[position].isExpanded
                notifyDataSetChanged()
            }
            
            holder.nameTV.text = allRaces[position].name
            holder.deleteIV.setOnClickListener {
                context.onDeleteClick(allRaces[position])
            }
            holder.itemView.findViewById<LinearLayout>(R.id.holder).setOnClickListener {
                context.onClick(allRaces[position])
            }

            holder.addBtn.setOnClickListener {
                subraceViewModel.add(
                    Subrace("name", "desc", "asd", "asd",allRaces[position].uid, SimpleDateFormat("dd MMM, yyyy - HH:mm").format(
                        Date()
                    ))
                )
                Toast.makeText(context.requireContext(), "test Added", Toast.LENGTH_LONG).show()
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

    override fun onClick(entity: Subrace) {
        (context.requireActivity() as MainActivity).toEditFragment(entity)
    }
}
