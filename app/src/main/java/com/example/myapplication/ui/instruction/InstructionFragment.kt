package com.example.myapplication.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.ui.racelist.SectionAdapter
import com.example.myapplication.viewmodel.SectionViewModel

class InstructionFragment : AbstractListFragment<Section>(){


    private lateinit var addBtn: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_racelist, container, false)
        recyclerView = fragment.findViewById(R.id.list)
        addBtn = fragment.findViewById(R.id.addBtn)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SectionViewModel::class.java]
        adapter = SectionAdapter(this, viewModel as SectionViewModel)
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
        return fragment
    }

    override fun onClick(entity: Section) {
        (activity as MainActivity).toEditFragment(entity)
    }
}