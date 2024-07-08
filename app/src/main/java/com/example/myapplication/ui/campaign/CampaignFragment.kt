package com.example.myapplication.ui.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.common.Consts
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.CampaignViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CampaignFragment : AbstractEditFragment<Campaign>() {

    //region VIEWS
    private lateinit var saveBtn: Button
    private lateinit var descriptionET: EditText
    private lateinit var nameET: EditText
    //endregion

    private var campaignID:Long = Long.MIN_VALUE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (requireActivity() as MainActivity).toFragment(Consts.FragmentTags.CAMPAIGNLIST_FRAGMENT)
                }
            })
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
            if (campaignID != Long.MIN_VALUE) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US)
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedCampaign= Campaign(
                       // description,
                        name,
                        currentDateAndTime)
                    updatedCampaign.uid = campaignID
                    viewModel.update(updatedCampaign)
                    Toast.makeText(activity, "${updatedCampaign.name} Updated..", Toast.LENGTH_LONG).show()
            }
            (activity as MainActivity).toFragment(Consts.FragmentTags.CAMPAIGNLIST_FRAGMENT)
        }
        return fragment
    }
    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val campaign = entity as Campaign
            campaignID = campaign.uid
            //descriptionET.setText(campaign.description)
            nameET.setText(campaign.name)
        }
    }
}