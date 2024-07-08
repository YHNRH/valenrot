package com.example.myapplication.ui.racelist

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.ui.definition.DefinitionAdapter
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.DefinitionViewModel
import com.example.myapplication.viewmodel.FieldViewModel

class DefinitionListFragment : AbstractListFragment<Definition>(){
    override val layout = R.layout.fragment_definitionlist
    override fun init() {
        val fieldViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[FieldViewModel::class.java]

        val definitionAdapter = DefinitionAdapter(this, fieldViewModel)

        recyclerView.adapter = definitionAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[DefinitionViewModel::class.java]

        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                definitionAdapter.updateList(it)
            }
        }
        addBtn.setOnClickListener {
            viewModel.add(Definition.default())
            Toast.makeText(this.requireContext(), "Раса добавлена", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(entity: Definition) {
        (activity as MainActivity).toEditFragment(entity)
    }

    fun updateDefinition(updatedEntity: Definition) {
        viewModel.update(updatedEntity)
    }
}