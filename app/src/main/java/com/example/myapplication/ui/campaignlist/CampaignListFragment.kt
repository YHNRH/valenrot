package com.example.myapplication.ui.campaignlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.racelist.CampaignViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CampaignListFragment : Fragment(), RaceClickInterface, RaceClickDeleteInterface  {

    lateinit var viewModel: CampaignViewModel
    private lateinit var campaignsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_campaignlist, container, false)
        campaignsRV = fragment.findViewById(R.id.list)
        addFAB = fragment.findViewById(R.id.idFAB)

        campaignsRV.layoutManager = LinearLayoutManager(requireActivity())

        val campaignRVAdapter = CampaignAdapter(requireActivity(), this, this)

        campaignsRV.adapter = campaignRVAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]

        viewModel.allCampaigns.observe(this.requireActivity()) { list ->
            list?.let {
                campaignRVAdapter.updateList(it)
            }
        }
        addFAB.setOnClickListener {
//            viewModel.addRace(Race(
//                1,
//                3,
//                54,
//                5,
//                1,
//                1,
//                1,
//                1,
//                1,
//                1,
//                "1",
//                "desc",
//                Date().time.toString()
//            ))
            Toast.makeText(this.requireContext(), "test Added", Toast.LENGTH_LONG).show()
        }
        return fragment
    }

    override fun onRaceClick(campaign: Campaign) {
        //(activity as MainActivity).toRaceEditFragment(campaign)
    }

    override fun onDeleteIconClick(campaign: Campaign) {
        viewModel.deleteRace(campaign)
        Toast.makeText(requireContext(), "${campaign.name} Deleted", Toast.LENGTH_LONG).show()
    }
}