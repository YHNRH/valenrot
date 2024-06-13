package com.example.myapplication.ui.racelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.viewModels
import com.example.myapplication.MainActivity

import com.example.myapplication.R
import com.example.myapplication.ui.raceeditor.RaceEditViewModel

class RaceListFragment : Fragment() {

    private val viewModel: RaceEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_racelist, container, false)

        val mListView = fragment.findViewById<ListView>(R.id.userlist)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, viewModel.getRaces())
        mListView.adapter = arrayAdapter

        mListView.setOnItemClickListener { _: AdapterView<*>, _: View, _: Int, _: Long ->
            (activity as MainActivity).toRaceEditFragment()
        }


        return fragment
    }
}