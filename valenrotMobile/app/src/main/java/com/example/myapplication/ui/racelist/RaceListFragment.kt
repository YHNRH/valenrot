package com.example.myapplication.ui.racelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.dialog.DeleteDialogFragment
import com.example.myapplication.viewmodel.RaceViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date

class RaceListFragment : Fragment(), RaceClickInterface, RaceClickDeleteInterface,
    DeleteDialogFragment.DialogListener {

    lateinit var viewModel: RaceViewModel
    private lateinit var racesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_racelist, container, false)
        racesRV = fragment.findViewById(R.id.list)
        addFAB = fragment.findViewById(R.id.idFAB)

        racesRV.layoutManager = LinearLayoutManager(requireActivity())

        val raceRVAdapter = RaceAdapter(requireActivity(), this, this)

        racesRV.adapter = raceRVAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[RaceViewModel::class.java]

        viewModel.allRaces.observe(this.requireActivity()) { list ->
            list?.let {
                raceRVAdapter.updateList(it)
            }
        }
        addFAB.setOnClickListener {
            viewModel.addRace(Race(
                1,
                3,
                54,
                5,
                1,
                1,
                1,
                1,
                1,
                1,
                "Описание",
                "Новая раса",
                SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            ))
            Toast.makeText(this.requireContext(), "test Added", Toast.LENGTH_LONG).show()
        }
        return fragment
    }

    override fun onRaceClick(race: Race) {
        (activity as MainActivity).toRaceEditFragment(race)
    }

    lateinit var raceToDelete: Race
    override fun onDeleteIconClick(race: Race) {
        raceToDelete = race
        DeleteDialogFragment().setListener(this).setEntity(race).show(parentFragmentManager, "DELETE_DIALOG")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.deleteRace(raceToDelete)
        Toast.makeText(requireContext(), "${raceToDelete.name} deleted", Toast.LENGTH_LONG).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {}
}