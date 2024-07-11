package com.example.myapplication.ui.campaign

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.core.common.Consts
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CampaignFragment : AbstractEditFragment<Campaign>() {
    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val campaign = entity as Campaign
            entityId = campaign.uid
            nameET.setText(campaign.title)
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]
    }

    override fun saveBtn() {
        val name = nameET.text.toString()
        if (entityId != Long.MIN_VALUE) {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US)
            val currentDateAndTime: String = sdf.format(Date())
            val updatedCampaign= Campaign(
                name,
                currentDateAndTime)
            updatedCampaign.uid = entityId
            viewModel.update(updatedCampaign)
            Toast.makeText(activity, "${updatedCampaign.title} Updated..", Toast.LENGTH_LONG).show()
        }
        (activity as MainActivity).toFragment(Consts.FragmentTags.CAMPAIGNLIST_FRAGMENT)
    }
}