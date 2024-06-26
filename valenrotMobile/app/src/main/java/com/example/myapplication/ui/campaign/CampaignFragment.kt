package com.example.myapplication.ui.campaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import java.text.SimpleDateFormat
import java.util.Date

class CampaignFragment : AbstractEditFragment() {

    //region VIEWS
    lateinit var saveBtn: Button
    lateinit var descriptionET: EditText
    lateinit var nameET: EditText
    //endregion
    private lateinit var viewModel: CampaignViewModel

    private var campaignID = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_campaignedit, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]
        //region VIEWS
        saveBtn = fragment.findViewById(R.id.save)
        descriptionET = fragment.findViewById(R.id.description)
        nameET = fragment.findViewById(R.id.name)
        //endregion

        saveBtn.setOnClickListener {
            val description = descriptionET.text.toString()
            val name = nameET.text.toString()
            if (campaignID != -1) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedCampaign= Campaign(
                       // description,
                        name,
                        currentDateAndTime)
                    updatedCampaign.uid = campaignID
                    viewModel.update(updatedCampaign)
                    Toast.makeText(activity, "Note Updated..", Toast.LENGTH_LONG).show()
            } else {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val insertCampaign = Campaign(
                       // description,
                        name,
                        currentDateAndTime)
                    viewModel.update(insertCampaign)
                    Toast.makeText(activity, "${insertCampaign.name} Added", Toast.LENGTH_LONG).show()
            }
            //(activity as MainActivity).toCampaignListFragment()
        }
        return fragment
    }
    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val campaign = entity as Campaign
            campaignID = campaign.uid
            saveBtn.text = "Update Note"
            //descriptionET.setText(campaign.description)
            nameET.setText(campaign.name)
        } else {
            campaignID = -1
            saveBtn.text = "Save Note"
        }

    }
}