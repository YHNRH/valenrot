package com.example.myapplication.ui.instruction

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
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.SectionViewModel
import com.example.myapplication.viewmodel.SubraceViewModel
import java.text.SimpleDateFormat
import java.util.Date

class SectionFragment : AbstractEditFragment<Section>() {

    //region VIEWS
    private lateinit var saveBtn: Button
    private lateinit var textET: EditText
    private lateinit var nameET: EditText
    //endregion


    private var sectionId = Long.MIN_VALUE
    private var parentId: Long? = Long.MIN_VALUE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (requireActivity() as MainActivity).toFragment(Consts.FragmentTags.INSTRUCTION_FRAGMENT)
                }
            })
        val fragment = inflater.inflate(R.layout.fragment_sectionedit, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SectionViewModel::class.java]
        //region VIEWS
        saveBtn = fragment.findViewById(R.id.save)
        textET = fragment.findViewById(R.id.text)
        nameET = fragment.findViewById(R.id.name)
        //endregion

        saveBtn.setOnClickListener {
            val description = textET.text.toString()
            val name = nameET.text.toString()
            if (sectionId != Long.MIN_VALUE) {
                    val updatedRace = Section(
                        name,
                        description,
                        parentId,
                        SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
                    )
                    updatedRace.uid = sectionId
                    viewModel.update(updatedRace)
                    Toast.makeText(activity, "Note Updated..", Toast.LENGTH_LONG).show()
            }
            (activity as MainActivity).toFragment(Consts.FragmentTags.INSTRUCTION_FRAGMENT)
        }
        return fragment
    }

    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val section = entity as Section
            sectionId = section.uid
            parentId = section.parentId
            saveBtn.text = "Update Note"
            nameET.setText(section.title)
            textET.setText(section.text)
        }
    }
}