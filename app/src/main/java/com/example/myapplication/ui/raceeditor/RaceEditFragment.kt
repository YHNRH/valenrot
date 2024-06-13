package com.example.myapplication.ui.raceeditor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.MainActivity

import com.example.myapplication.R

class RaceEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var fragment = inflater.inflate(R.layout.fragment_raceedit, container, false)

        fragment.findViewById<Button>(R.id.save).setOnClickListener {
            (activity as MainActivity).toRaceListFragment()
        }

        return fragment
    }
}