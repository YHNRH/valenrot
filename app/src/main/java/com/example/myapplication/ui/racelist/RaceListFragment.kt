package com.example.myapplication.ui.racelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.interfaces.AbstractListFragment
import com.example.myapplication.viewmodel.RaceViewModel
import com.example.myapplication.viewmodel.SubraceViewModel

class RaceListFragment : AbstractListFragment<Race>(){

    private lateinit var racesRV: RecyclerView
    private lateinit var addBtn: ImageView
    private lateinit var subraceViewModel:SubraceViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_racelist, container, false)
        racesRV = fragment.findViewById(R.id.list)
        addBtn = fragment.findViewById(R.id.addBtn)

        racesRV.layoutManager = LinearLayoutManager(requireActivity())

        subraceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SubraceViewModel::class.java]

        val raceRVAdapter = RaceAdapter(this, subraceViewModel)

        racesRV.adapter = raceRVAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[RaceViewModel::class.java]

        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                raceRVAdapter.updateList(it)
            }
        }
        addBtn.setOnClickListener {
            viewModel.add(Race.default())
            Toast.makeText(this.requireContext(), "Раса добавлена", Toast.LENGTH_LONG).show()
        }
        return fragment
    }

    override fun onClick(entity: Race) {
        (activity as MainActivity).toEditFragment(entity)
    }
}