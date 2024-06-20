package com.example.myapplication.ui.campaignlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.dialog.DeleteDialogFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date

class CampaignListFragment : Fragment(), CampaignClickInterface, CampaignClickDeleteInterface,
    DeleteDialogFragment.DialogListener {

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
            viewModel.addCampaign(Campaign(
                "Новая компания",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            ))
        }
        return fragment
    }

    override fun onCampaignClick(campaign: Campaign) {
        (activity as MainActivity).toCampaignEditFragment(campaign)
    }

    lateinit var campaignToDelete: Campaign
    override fun onDeleteIconClick(campaign: Campaign) {
        campaignToDelete = campaign
        DeleteDialogFragment().setListener(this).setEntity(campaign)
            .show(parentFragmentManager, "DELETE_DIALOG")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.deleteCampaign(campaignToDelete)
        Toast.makeText(requireContext(), "${campaignToDelete.name} Deleted", Toast.LENGTH_LONG)
            .show()
    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {}
}