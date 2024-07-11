package com.example.myapplication.ui.campaign

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.CampaignViewModel

class CampaignListFragment : AbstractListFragment<Campaign>(){

    override val layout = R.layout.fragment_list

    override fun init() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]

        adapter = CampaignRVAdapter(
            this,
            viewModel as CampaignViewModel)

        addBtn.setOnClickListener {
            viewModel.add(Campaign.default())
        }
    }

    override fun onClick(entity: Campaign) {
        (activity as MainActivity).toEditFragment(entity)
    }
}