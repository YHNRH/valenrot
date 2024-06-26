package com.example.myapplication.ui.race

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.viewmodel.RaceViewModel
import java.text.SimpleDateFormat
import java.util.Date

class RaceFragment : AbstractEditFragment(){

    //region VIEWS
    lateinit var saveBtn: Button
    lateinit var strengthET: EditText
    lateinit var agilityET: EditText
    lateinit var charismaET: EditText
    lateinit var intelligenceET: EditText
    lateinit var perceptionET: EditText
    lateinit var healthET: EditText
    lateinit var dodgeET: EditText
    lateinit var durabilityET: EditText
    lateinit var accuracyET: EditText
    lateinit var damageET: EditText
    lateinit var descriptionET: EditText
    lateinit var nameET: EditText
    //endregion
    private lateinit var viewModel: RaceViewModel

    private var raceID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_raceedit, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[RaceViewModel::class.java]
        //region VIEWS
        saveBtn = fragment.findViewById(R.id.save)
        strengthET = fragment.findViewById(R.id.strength)
        agilityET = fragment.findViewById(R.id.agility)
        charismaET = fragment.findViewById(R.id.charisma)
        intelligenceET = fragment.findViewById(R.id.intelligence)
        perceptionET = fragment.findViewById(R.id.perception)
        healthET = fragment.findViewById(R.id.health)
        dodgeET = fragment.findViewById(R.id.dodge)
        durabilityET = fragment.findViewById(R.id.durability)
        accuracyET = fragment.findViewById(R.id.accuracy)
        damageET = fragment.findViewById(R.id.damage)
        descriptionET = fragment.findViewById(R.id.description)
        nameET = fragment.findViewById(R.id.name)
        //endregion

        saveBtn.setOnClickListener {
            val strength = Integer.parseInt(strengthET.text.toString())
            val agility = Integer.parseInt(agilityET.text.toString())
            val charisma = Integer.parseInt(charismaET.text.toString())
            val intelligence = Integer.parseInt(intelligenceET.text.toString())
            val perception = Integer.parseInt(perceptionET.text.toString())
            val health = Integer.parseInt(healthET.text.toString())
            val dodge =  Integer.parseInt(dodgeET.text.toString())
            val durability = Integer.parseInt(durabilityET.text.toString())
            val accuracy = Integer.parseInt(accuracyET.text.toString())
            val damage = Integer.parseInt(damageET.text.toString())
            val description = descriptionET.text.toString()
            val name = nameET.text.toString()
            if (raceID != -1) {
                    val currentDateAndTime: String = SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
                    val updatedRace = Race(
                        strength,
                        agility,
                        charisma,
                        intelligence,
                        perception,
                        health,
                        dodge,
                        durability,
                        accuracy,
                        damage,
                        description,
                        name,
                        currentDateAndTime)
                    updatedRace.uid = raceID
                    viewModel.update(updatedRace)
                    Toast.makeText(activity, "Note Updated..", Toast.LENGTH_LONG).show()
            } else {
                    val insertRace = Race(
                        strength,
                        agility,
                        charisma,
                        intelligence,
                        perception,
                        health,
                        dodge,
                        durability,
                        accuracy,
                        damage,
                        description,
                        name,
                        SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date()))
                    viewModel.add(insertRace)
                    Toast.makeText(activity, "${insertRace.name} Added", Toast.LENGTH_LONG).show()
            }
            //(activity as MainActivity).toRaceListFragment()
        }
        return fragment
    }
    override fun refresh(entity: BaseEntity?) {
        if (entity != null) {
            val race = entity as Race
            raceID = race.uid
            saveBtn.text = "Update Note"
            strengthET.setText(race.strength.toString())
            agilityET.setText(race.agility.toString())
            charismaET.setText(race.charisma.toString())
            intelligenceET.setText(race.intelligence.toString())
            perceptionET.setText(race.perception.toString())
            healthET.setText(race.health.toString())
            dodgeET.setText(race.dodge.toString())
            durabilityET.setText(race.durability.toString())
            accuracyET.setText(race.accuracy.toString())
            damageET.setText(race.damage.toString())
            descriptionET.setText(race.description)
            nameET.setText(race.name)
        } else {
            raceID = -1
            saveBtn.text = "Save Note"
        }
    }
}