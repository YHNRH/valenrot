package com.example.myapplication.ui.racelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Definition
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.DefinitionViewModel
import com.example.myapplication.viewmodel.FieldViewModel
import com.example.myapplication.viewmodel.RaceViewModel
import com.example.myapplication.viewmodel.SubraceViewModel

class DefinitionListFragment : AbstractListFragment<Definition>(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_definitionlist, container, false)
        recyclerView = fragment.findViewById(R.id.list)
        val addBtn = fragment.findViewById<ImageView>(R.id.addBtn)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

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
        return fragment
    }

    override fun onClick(entity: Definition) {
        (activity as MainActivity).toEditFragment(entity)
    }
}