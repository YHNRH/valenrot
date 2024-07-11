package com.example.myapplication.ui.instruction

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.core.common.Consts
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.SectionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SectionFragment : AbstractEditFragment<Section>() {

    private var parentId: Long? = Long.MIN_VALUE
    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SectionViewModel::class.java]
    }

    override fun saveBtn(){
        val description = textET.text.toString()
        val name = nameET.text.toString()
        if (entityId != Long.MIN_VALUE) {
            val updatedRace = Section(
                name,
                description,
                parentId,
                SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US).format(Date()),
                isList.isChecked

            )
            updatedRace.uid = entityId
            viewModel.update(updatedRace)
            Toast.makeText(activity, "Note Updated..", Toast.LENGTH_LONG).show()
        }
        (activity as MainActivity).toFragment(Consts.FragmentTags.INSTRUCTION_FRAGMENT)
    }

    override fun refresh(entity: BaseEntity?) {
        if (entity is Section) {
            entityId = entity.uid
            parentId = entity.parentId
            isList.visibility = if(entity.parentId != null) View.GONE else View.VISIBLE
            isList.isChecked = entity.isList
            nameET.setText(entity.title)
            textET.setText(entity.text)
        }
    }
}