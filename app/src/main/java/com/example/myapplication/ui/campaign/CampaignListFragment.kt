package com.example.myapplication.ui.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import java.text.SimpleDateFormat
import java.util.Date

class CampaignListFragment : AbstractListFragment<Campaign>(){

    override val layout = R.layout.fragment_campaignlist

    override fun init() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]
        val characterViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CharacterViewModel::class.java]

        adapter = CampaignRVAdapter(
            this,
            characterViewModel)

        recyclerView.adapter = adapter

        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

        addBtn.setOnClickListener {
            viewModel.add(Campaign(
                "Новая компания",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            ))
        }
    }

    override fun onClick(entity: Campaign) {
        (activity as MainActivity).toEditFragment(entity)
    }
}