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
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder
import com.example.myapplication.ui.interfaces.OnClickEntityInterface
import java.text.SimpleDateFormat
import java.util.Date

class RaceAdapter(
    val context: RaceListFragment,
    private val subraceViewModel : SubraceViewModel
    ) :
        AbstractRVAdapter<Race>(),
        OnClickEntityInterface<Subrace>{

        private lateinit var subraceRV: RecyclerView

        inner class ViewHolder(itemView: View) : AbstractViewHolder(itemView) {
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

    override fun onBindViewHolder(holder: AbstractViewHolder , position: Int) {
            holder as ViewHolder
            subraceRV = holder.itemView.findViewById(R.id.list)
            subraceRV.layoutManager = LinearLayoutManager(context.requireContext())
            val subraceRVAdapter = SubraceRVAdapter(this)

            subraceRV.adapter = subraceRVAdapter
            subraceViewModel.allEntities.observe(context) { list ->
                list?.let {
                    subraceRVAdapter.updateList(it, allEntities[position])
                }
            }

            subraceRV.visibility =  if (allEntities[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE
            holder.addBtn.visibility = if (allEntities[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

            if (allEntities[position].isExpanded){
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
                allEntities[position].isExpanded = !allEntities[position].isExpanded
                notifyDataSetChanged()
            }
            
            holder.nameTV.text = allEntities[position].title
            holder.deleteIV.setOnClickListener {
                context.onDeleteClick(allEntities[position])
            }
            holder.itemView.findViewById<LinearLayout>(R.id.holder).setOnClickListener {
                context.onClick(allEntities[position])
            }

            holder.addBtn.setOnClickListener {
                subraceViewModel.add(
                    Subrace("name", "desc", "asd", "asd",allEntities[position].uid, SimpleDateFormat("dd MMM, yyyy - HH:mm").format(
                        Date()
                    ))
                )
                Toast.makeText(context.requireContext(), "test Added", Toast.LENGTH_LONG).show()
            }
        }

        fun updateList(newList: List<Race>) {
            allEntities.clear()
            allEntities.addAll(newList)
            notifyDataSetChanged()
        }

    override fun onClick(entity: Subrace) {
        (context.requireActivity() as MainActivity).toEditFragment(entity)
    }
}
