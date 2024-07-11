package com.example.myapplication.ui.interfaces

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.common.flip
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.viewmodel.BaseViewModel

abstract class AbstractRVAdapter<T:BaseEntity>(
    open val context: AbstractListFragment<T>,
    private val viewModel: BaseViewModel<T>
) : RecyclerView.Adapter<AbstractViewHolder>(){
    val allEntities = ArrayList<T>()

    private lateinit var recyclerView: RecyclerView
    final override fun getItemCount(): Int {
        return allEntities.size
    }
    open class ViewHolder(itemView: View) : AbstractViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false
        )

        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder as ViewHolder
        val entity = allEntities[position]
        recyclerView = holder.itemView.findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(context.requireContext())
        val childAdapter = invoke(context, viewModel, entity)

        viewModel.allEntities.observe(context) { list ->
            list?.let {
                try {
                    childAdapter.updateList(it.filter { (it as Section).parentId == entity.uid })
                } catch (e: Exception){
                    //TODO
                }
            }
        }

        recyclerView.adapter = childAdapter
        recyclerView.visibility =  if (entity.isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE
        holder.addBtn.visibility = if (entity.isExpanded)  RecyclerView.VISIBLE else RecyclerView.GONE

        if (entity.isExpanded){
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
            entity.isExpanded = !entity.isExpanded
            notifyDataSetChanged()
        }

        holder.nameTV.text = entity.title
        holder.deleteIV.setOnClickListener {
            context.onDeleteClick(entity)
        }
        holder.itemView.findViewById<LinearLayout>(R.id.holder).setOnClickListener {
            context.onClick(entity)
        }

        holder.addBtn.setOnClickListener {
                       viewModel.add(
                            addChildItem(entity.uid)
                        )
        }
        onBindViewHolderExtension(holder, entity)
    }

    abstract fun onBindViewHolderExtension(holder: AbstractViewHolder, entity: T)

    abstract fun invoke(
        context: AbstractListFragment<T>,
        viewModel: BaseViewModel<*>,
        parentEntity: T? = null)
    : AbstractRVAdapter<T>
    abstract fun updateList(newList: List<T>)
    abstract fun addChildItem(parentId: Long? = null) : T
}