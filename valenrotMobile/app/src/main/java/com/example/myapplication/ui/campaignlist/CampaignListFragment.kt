package com.example.myapplication.ui.campaignlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date

class CampaignListFragment : AbstractListFragment<Campaign>(), CampaignClickInterface {

    private lateinit var campaignsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_campaignlist, container, false)
        campaignsRV = fragment.findViewById(R.id.list)
        addFAB = fragment.findViewById(R.id.idFAB)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]

        campaignsRV.layoutManager = LinearLayoutManager(requireActivity())

        val characterViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CharacterViewModel::class.java]

        val campaignRVAdapter = CampaignRVAdapter(
            requireActivity(),
            characterViewModel,
            this,
            this,
            this)

        campaignsRV.adapter = campaignRVAdapter

        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                campaignRVAdapter.updateList(it)
            }
        }

        addFAB.setOnClickListener {
            viewModel.add(Campaign(
                "Новая компания",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            ))
        }
        return fragment
    }

    override fun onCampaignClick(campaign: Campaign) {
        (activity as MainActivity).toCampaignEditFragment(campaign)
    }
}