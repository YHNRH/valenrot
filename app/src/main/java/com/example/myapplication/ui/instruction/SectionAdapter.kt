package com.example.myapplication.ui.instruction

import com.example.myapplication.MainActivity
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder
import com.example.myapplication.ui.interfaces.OnClickEntityInterface
import com.example.myapplication.viewmodel.BaseViewModel
import com.example.myapplication.viewmodel.SectionViewModel

class SectionAdapter(
    override val context: AbstractListFragment<Section>,
    viewModel: SectionViewModel
    ) :
        AbstractRVAdapter<Section>(context, viewModel),
        OnClickEntityInterface<Section>{

        override fun updateList(newList: List<Section>) {
            val old = allEntities.clone()
            allEntities.clear()
            allEntities.addAll(newList)
            allEntities.forEach {
                it.isExpanded = (old as ArrayList<Section>).find { o -> it.uid == o.uid }?.isExpanded == true
            }
            notifyDataSetChanged()
        }
    override fun invoke(
        context: AbstractListFragment<Section>,
        viewModel: BaseViewModel<*>,
        parentEntity: Section?
    ): AbstractRVAdapter<Section> {
        return SectionAdapter(context, viewModel as SectionViewModel)
    }

    override fun onBindViewHolderExtension(holder: AbstractViewHolder, entity: Section) {
      //  holder.expandBtn.visibility = if (entity.parentId == null)  RecyclerView.VISIBLE else RecyclerView.GONE
    }

    override fun addChildItem(parentId: Long?): Section {
            val s = Section.default()
            s.parentId = parentId
            return s
        }

    override fun onClick(entity: Section) {
        (context.requireActivity() as MainActivity).toEditFragment(entity)
    }
}
