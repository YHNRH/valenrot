package com.example.myapplication.ui.racelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import android.graphics.BitmapFactory
import android.widget.LinearLayout
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.core.common.flip
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.instruction.InstructionFragment
import com.example.myapplication.ui.interfaces.OnClickEntityInterface
import com.example.myapplication.viewmodel.SectionViewModel
import java.text.SimpleDateFormat
import java.util.Date

class SectionAdapter(
    val context: InstructionFragment,
    val sectionViewModel: SectionViewModel
    ) :
        RecyclerView.Adapter<SectionAdapter.ViewHolder>(),
        OnClickEntityInterface<Section>{

        private val allSections = ArrayList<Section>()
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
            val childAdapter = SectionAdapter(context, sectionViewModel)

            subraceRV.adapter = childAdapter
            sectionViewModel.allEntities.observe(context) { list ->
                list?.let {
                    childAdapter.updateList(it, allSections[position])
                }
            }

            subraceRV.visibility =  if (allSections[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE
            holder.addBtn.visibility = if (allSections[position].isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

            if (allSections[position].isExpanded){
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
                allSections[position].isExpanded = !allSections[position].isExpanded
                notifyDataSetChanged()
            }
            
            holder.nameTV.text = allSections[position].name
            holder.deleteIV.setOnClickListener {
                context.onDeleteClick(allSections[position])
            }
            holder.itemView.findViewById<LinearLayout>(R.id.holder).setOnClickListener {
                context.onClick(allSections[position])
            }

            holder.addBtn.setOnClickListener {
                sectionViewModel.add(
                    Section("name",
                        "desc",
                        allSections[position].uid,
                        SimpleDateFormat("dd MMM, yyyy - HH:mm").format(
                        Date()
                    ))
                )
                Toast.makeText(context.requireContext(), "test Added", Toast.LENGTH_LONG).show()
            }
        }

        override fun getItemCount(): Int {
            return allSections.size
        }

        fun updateList(newList: List<Section>) {
            allSections.clear()
            allSections.addAll(newList.filter { it.parentId == null})
            notifyDataSetChanged()
        }

        fun updateList(newList: List<Section>, race: Section) {
            allSections.clear()
            allSections.addAll(newList.filter { it.parentId == race.uid})
            notifyDataSetChanged()
        }

    override fun onClick(entity: Section) {
        (context.requireActivity() as MainActivity).toEditFragment(entity)
    }
}
