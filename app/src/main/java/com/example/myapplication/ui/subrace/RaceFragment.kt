package com.example.myapplication.ui.subrace

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
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.SubraceViewModel
import java.text.SimpleDateFormat
import java.util.Date

class SubraceFragment : AbstractEditFragment() {

    //region VIEWS
    lateinit var saveBtn: Button
    lateinit var activeAbilityET: EditText
    lateinit var passiveAbilityET: EditText
    lateinit var descriptionET: EditText
    lateinit var nameET: EditText
    //endregion
    private lateinit var viewModel: SubraceViewModel

    private var subraceID = Long.MIN_VALUE
    private var raceID = Long.MIN_VALUE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (requireActivity() as MainActivity).toFragment(Consts.FragmentTags.RACELIST_FRAGMENT)
                }
            })
        val fragment = inflater.inflate(R.layout.fragment_subraceedit, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SubraceViewModel::class.java]
        //region VIEWS
        saveBtn = fragment.findViewById(R.id.save)
        activeAbilityET = fragment.findViewById(R.id.active_ability)
        passiveAbilityET = fragment.findViewById(R.id.passive_ability)
        descriptionET = fragment.findViewById(R.id.description)
        nameET = fragment.findViewById(R.id.name)
        //endregion

        saveBtn.setOnClickListener {
            val activeAbility = activeAbilityET.text.toString()
            val passiveAbility = passiveAbilityET.text.toString()
            val description = descriptionET.text.toString()
            val name = nameET.text.toString()
            if (subraceID != Long.MIN_VALUE) {
                    val updatedRace = Subrace(
                        name,
                        description,
                        activeAbility,
                        passiveAbility,
                        raceID,
                        SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
                    )
                    updatedRace.uid = subraceID
                    viewModel.update(updatedRace)
                    Toast.makeText(activity, "Note Updated..", Toast.LENGTH_LONG).show()
            }
            //(activity as MainActivity).toRaceListFragment()
        }
        return fragment
    }

    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val subrace = entity as Subrace
            subraceID = subrace.uid
            raceID = subrace.raceId
            saveBtn.text = "Update Note"
            passiveAbilityET.setText(subrace.passiveAbility)
            activeAbilityET.setText(subrace.activeAbility)
            descriptionET.setText(subrace.description)
            nameET.setText(subrace.name)
        }
    }
}