package com.example.myapplication.ui.roll

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.core.api.Common
import com.example.myapplication.core.common.observeOnce
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.RaceWithSubraces
import com.example.myapplication.ui.campaignlist.CampaignSpinnerAdapter
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import com.example.myapplication.viewmodel.RaceViewModel
import com.example.myapplication.viewmodel.SectionViewModel
import com.example.myapplication.viewmodel.SubraceViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random


class RollFragment : Fragment() {

    private lateinit var raceViewModel: RaceViewModel
    private lateinit var subraceViewModel: SubraceViewModel
    private lateinit var campaignViewModel: CampaignViewModel
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var spinner:Spinner
    private lateinit var nameET:EditText
    private lateinit var rollRaceNumberTV : TextView
    private lateinit var rollTemperNumberTV : TextView
    private lateinit var rollRaceTV : TextView
    private lateinit var rollTemperTV : TextView
    private lateinit var rolledRace : Race
    private var rolledTemper = 0
    private var apiService = Common.apiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_main, container, false)
        fragment.findViewById<Button>(R.id.roll_btn).setOnClickListener {view -> roll(view)}
        fragment.findViewById<Button>(R.id.save_btn).setOnClickListener { save()}
        fragment.findViewById<TextView>(R.id.uploadBtn).setOnClickListener { uploadInstruction()}
        fragment.findViewById<TextView>(R.id.downloadBtn).setOnClickListener { downloadInstruction()}
        nameET = fragment.findViewById(R.id.name)
        rollRaceNumberTV = fragment.findViewById(R.id.roll_tw)
        rollTemperNumberTV = fragment.findViewById(R.id.roll_tw1)
        rollRaceTV = fragment.findViewById(R.id.race)
        rollTemperTV = fragment.findViewById(R.id.temper)

        campaignViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]

        characterViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CharacterViewModel::class.java]

        raceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[RaceViewModel::class.java]

        subraceViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SubraceViewModel::class.java]

        sectionViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SectionViewModel::class.java]

        spinner = fragment.findViewById(R.id.spinner)

        val adapter = CampaignSpinnerAdapter(requireContext())
        spinner.adapter = adapter
        campaignViewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }
        return fragment
    }

    private fun roll(view: View) {

        val root = view.rootView

        rollRaceNumberTV.text = ""
        rollTemperNumberTV.text = ""
        val imageView = root.findViewById<ImageView>(R.id.myImageView)
        val imageView1 = root.findViewById<ImageView>(R.id.myImageView1)

        Glide.with(this)
            .load(R.drawable.roll_green)
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val res = resource as GifDrawable
                    res.setLoopCount(1)
                    res.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable) {
                            rollTemper()
                            rollName()
                        }
                    })

                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }
            })
            .into(imageView);

        Glide.with(this)
            .load(R.drawable.roll_red)
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val res = resource as GifDrawable
                    res.setLoopCount(1)
                    res.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable) {
                            rollRace()
                        }
                    })

                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }
            })
            .into(imageView1);

    }

    private fun rollRace(){
            raceViewModel.allEntities.observe(this.requireActivity()) { list ->
                list?.let {
                    if (list.isNotEmpty()) {
                        val rnd = Random.nextInt(1, list.size + 1)
                        val value = list[rnd - 1]
                        rollRaceNumberTV.text = rnd.toString()
                        rollRaceTV.text = value.name
                        rolledRace = value
                    } else {
                        context?.let {
                            Toast.makeText(context,
                                "Отсутствуют расы",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
    }

    private fun rollTemper(){
        val rnd = Random.nextInt(1, 21)
        rollTemperNumberTV.text = rnd.toString()
        rollTemperTV.text = rnd.toString()
        rolledTemper = rnd
    }

    private fun rollName(){
        apiService.getRandomNames().enqueue(object : Callback<Array<String>> {
            override fun onFailure(call: Call<Array<String>>, t: Throwable) {
                Toast.makeText(requireContext(),
                    "Ошибка рандомайзера имени!",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Array<String>>, response: Response<Array<String>>) {
                if (response.body() != null){
                    val names = response.body()!!
                    val value = names[Random.nextInt(names.size)]
                    nameET.setText(value)
                }
            }
        })
    }

    private fun save() {
        if (this::rolledRace.isInitialized && rolledTemper != 0 && spinner.selectedItem !== null){
            val campaign =  spinner.selectedItem as Campaign
            val name     =  nameET.text.toString()
            val currentDateAndTime: String = SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            characterViewModel.addCharacter(Character(
                rolledRace.uid,
                campaign.uid,
                rolledTemper,
                name,
                currentDateAndTime
            ))
            Toast.makeText(requireContext(),
                campaign.name + "\n" +
                        campaign.uid + "\n" +
                        name + "\n",
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(),
                "Ошибка сохранения персонажа",
                Toast.LENGTH_SHORT).show()
        }

    }

    private fun uploadInstruction(){
        sectionViewModel.allEntities.observeOnce(this.requireActivity()) { list ->
            apiService.uploadInstruction(list).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(requireContext(),
                        "Ошибка выгрузки инструкции!",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(requireContext(),
                        "Инструкция успешно выгружена",
                        Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
    private fun downloadInstruction(){
        apiService.downloadInstruction().enqueue(object : Callback<List<RaceWithSubraces>> {
            override fun onResponse(
                p0: Call<List<RaceWithSubraces>>,
                response: Response<List<RaceWithSubraces>>
            ) {
                if (response.body() != null){
                    raceViewModel.addAll(response.body()!!, subraceViewModel)
                }
            Toast.makeText(requireContext(),
                "Инструкция успешно скачана",
                Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(p0: Call<List<RaceWithSubraces>>, p1: Throwable) {
                Toast.makeText(requireContext(),
                    "Ошибка скачивания инструкции!",
                    Toast.LENGTH_SHORT).show()
            }

        })
    }

}
