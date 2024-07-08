package com.example.myapplication.ui.instruction

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.ui.interfaces.AbstractRVAdapter
import com.example.myapplication.viewmodel.BaseViewModel
import com.example.myapplication.viewmodel.DefinitionViewModel
import com.example.myapplication.viewmodel.SectionViewModel

class InstructionFragment : AbstractListFragment<Section>(){
    override fun onClick(entity: Section) {
        (activity as MainActivity).toEditFragment(entity)
    }

    override val layout = R.layout.fragment_racelist
    override fun init() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SectionViewModel::class.java]
        val definitionViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[DefinitionViewModel::class.java]
        adapter = SectionAdapter(this, viewModel as SectionViewModel, definitionViewModel)
        recyclerView.adapter = adapter



        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                (adapter as SectionAdapter).updateList(it)
            }
        }
        addBtn.setOnClickListener {
            viewModel.add(Section.default())
            Toast.makeText(this.requireContext(), "Раса добавлена", Toast.LENGTH_LONG).show()
        }
    }
}