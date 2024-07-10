package com.example.myapplication.ui.instruction

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
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.MainActivity
import com.example.myapplication.core.common.flip
import com.example.myapplication.core.common.observeOnce
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder
import com.example.myapplication.ui.interfaces.OnClickEntityInterface
import com.example.myapplication.viewmodel.SectionViewModel

class SectionAdapter(
    val context: AbstractListFragment<Section>,
    private val sectionViewModel: SectionViewModel
    ) :
        AbstractRVAdapter<Section>(),
        OnClickEntityInterface<Section>{

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

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
            holder as ViewHolder
            subraceRV = holder.itemView.findViewById(R.id.list)
            subraceRV.layoutManager = LinearLayoutManager(context.requireContext())
            val childAdapter = SectionAdapter(context, sectionViewModel)

            subraceRV.adapter = childAdapter
            sectionViewModel.allEntities.observe(context) { list ->
                list?.let {
                    childAdapter.updateList(it, allEntities[position])
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
//                definitionViewModel.allEntities.observeOnce(context) {
//                    val checkedItem = intArrayOf(-1)
//                    val alertDialog = AlertDialog.Builder(context.requireContext())
//                    alertDialog.setTitle("Choose an Item")
//

//                    alertDialog.setSingleChoiceItems((it.map { it.title }).toTypedArray(), checkedItem[0]) { dialog, which ->
//                        checkedItem[0] = which
//                        //tvSelectedItemPreview.setText("Selected Item is : " + listItems[which])
//                        sectionViewModel.add(
//                            Section(
//                                "name",
//                                "desc",
//                                allEntities[position].uid,
//                                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date()),
//                                it[which].uid
//                            )
//                        )
//                        dialog.dismiss()
//                    }
//                    alertDialog.setNegativeButton("Cancel") { dialog, which -> }
//                    val customAlertDialog = alertDialog.create()
//                    customAlertDialog.show()

                //}
            }
        }

        override fun updateList(newList: List<Section>) {
            allEntities.clear()
            allEntities.addAll(newList.filter { it.parentId == null})
            notifyDataSetChanged()
        }

        fun updateList(newList: List<Section>, race: Section) {
            allEntities.clear()
            allEntities.addAll(newList.filter { it.parentId == race.uid})
            notifyDataSetChanged()
        }

    override fun onClick(entity: Section) {
        (context.requireActivity() as MainActivity).toEditFragment(entity)
    }
}
