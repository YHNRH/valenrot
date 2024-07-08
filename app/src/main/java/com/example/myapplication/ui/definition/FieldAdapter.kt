package com.example.myapplication.ui.racelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Field
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder

class FieldAdapter(
    val context: DefinitionAdapter
) : AbstractRVAdapter<Field>(){

        inner class ViewHolder(itemView: View) : AbstractViewHolder(itemView) {
            val titleET = itemView.findViewById<EditText>(R.id.title)
            val deleteBtn = itemView.findViewById<ImageView>(R.id.delete)
            val addBtn = itemView.findViewById<TextView>(R.id.addBtn)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_field,
                parent, false
            )

            return ViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder as ViewHolder
        holder.titleET.setText(allEntities[position].title)
        holder.titleET.onFocusChangeListener = View.OnFocusChangeListener { v, b ->
            if (!b){
                val updatedEntity = allEntities[position]
                updatedEntity.title = (v as EditText).text.toString()
                context.updateField(updatedEntity)
            }
        }
        holder.deleteBtn.setOnClickListener {
                //characterClickDeleteInterface.onDeleteClick(allCharacter[position].character)
            }
            holder.itemView.setOnClickListener {
                //context.onClick(allSubrace[position])
            }

        }

        fun updateList(newList: List<Field>, parent: Definition) {
            allEntities.clear()
            val test = newList.filter { it.parentId == parent.uid}
            allEntities.addAll(test)
            notifyDataSetChanged()
        }
    }

