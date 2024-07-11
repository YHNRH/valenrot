package com.example.myapplication.ui.campaign

import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.ui.interfaces.AbstractViewHolder
import com.example.myapplication.viewmodel.BaseViewModel
import com.example.myapplication.viewmodel.CampaignViewModel

class CampaignRVAdapter(
    activity : AbstractListFragment<Campaign>,
    characterViewModel : CampaignViewModel
    ) :
        AbstractRVAdapter<Campaign>(activity, characterViewModel){
        override fun updateList(newList: List<Campaign>) {
            allEntities.clear()
            allEntities.addAll(newList)
            notifyDataSetChanged()
        }

    override fun invoke(
        context: AbstractListFragment<Campaign>,
        viewModel: BaseViewModel<*>,
        parentEntity: Campaign?
    ): AbstractRVAdapter<Campaign> {
        return CampaignRVAdapter(context, viewModel as CampaignViewModel)
    }

    override fun onBindViewHolderExtension(holder: AbstractViewHolder, entity: Campaign) {
        TODO("Not yet implemented")
    }

    override fun addChildItem(parentId: Long?): Campaign {
        TODO("Not yet implemented")
    }
}
