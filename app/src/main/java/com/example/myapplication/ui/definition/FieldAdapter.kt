package com.example.myapplication.ui.definition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Field
import com.example.myapplication.ui.campaign.CampaignSpinnerAdapter
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder

class FieldAdapter(
    val context: DefinitionAdapter
) : AbstractRVAdapter<Field>(){

    val list = listOf("string", "int")
        inner class ViewHolder(itemView: View) : AbstractViewHolder(itemView) {
            val titleET = itemView.findViewById<EditText>(R.id.title)
            val deleteBtn = itemView.findViewById<ImageView>(R.id.delete)
            val addBtn = itemView.findViewById<TextView>(R.id.addBtn)
            val spinner : Spinner = itemView.findViewById(R.id.spinner)
        }

    override fun updateList(newList: List<Field>) {
        TODO("Not yet implemented")
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
        holder.spinner.setSelection(list.indexOf(allEntities[position].type))
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


        val adapter = CampaignSpinnerAdapter<String>(context.context.requireContext())
        holder.spinner.adapter = adapter
        adapter.updateList(list)
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val updatedEntity = allEntities[position]
                updatedEntity.type = list[position]
              //  context.updateField(updatedEntity)
            }

        }
        }

        fun updateList(newList: List<Field>, parent: Definition) {
            allEntities.clear()
            val test = newList.filter { it.parentId == parent.uid}
            allEntities.addAll(test)
            notifyDataSetChanged()
        }
    }

