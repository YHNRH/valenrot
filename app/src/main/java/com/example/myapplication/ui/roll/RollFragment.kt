package com.example.myapplication.ui.roll

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.campaignlist.CampaignSpinnerAdapter
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import com.example.myapplication.viewmodel.RaceViewModel
import com.example.myapplication.viewmodel.RollViewModel
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.properties.Delegates
import kotlin.random.Random


class RollFragment : Fragment() {

    //private val viewModel: RollViewModel by viewModels()
    lateinit var raceViewModel: RaceViewModel
    lateinit var campaignViewModel: CampaignViewModel
    lateinit var characterViewModel: CharacterViewModel
    lateinit var spinner:Spinner
    lateinit var nameET:EditText
    lateinit var rollRaceNumberTV:TextView
    lateinit var rollTemperNumberTV:TextView
    lateinit var rollRaceTV:TextView
    lateinit var rollTemperTV:TextView

    lateinit var rolledRace :Race
    var rolledTemper = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = inflater.inflate(R.layout.fragment_main, container, false)
        fragment.findViewById<Button>(R.id.roll_btn).setOnClickListener {view -> roll(view)}
        fragment.findViewById<Button>(R.id.save_btn).setOnClickListener { save()}
        nameET = fragment.findViewById(R.id.name)
        rollRaceNumberTV = fragment.findViewById<TextView>(R.id.roll_tw)
        rollTemperNumberTV = fragment.findViewById<TextView>(R.id.roll_tw1)
        rollRaceTV = fragment.findViewById<TextView>(R.id.race)
        rollTemperTV = fragment.findViewById<TextView>(R.id.temper)

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

        spinner = fragment.findViewById(R.id.spinner)

        val adapter =
            CampaignSpinnerAdapter(requireContext())
        spinner.adapter = adapter
        campaignViewModel.allCampaigns.observe(this.requireActivity()) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
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
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val res = resource as GifDrawable
                    res.setLoopCount(1)
                    res.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable) {
                            rollTemper()
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
            raceViewModel.allRaces.observe(this.requireActivity()) { list ->
                list?.let {
                    val rnd = Random.nextInt(1, list.size+1)
                    val value = list[rnd - 1]
                    rollRaceNumberTV.text = rnd.toString()
                    rollRaceTV.text = value.name
                    rolledRace = value
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
	val names = arrayOf("", "")
    }

    private fun save() {

        if (this::rolledRace.isInitialized && rolledTemper != 0){
            val campaign =  spinner.selectedItem as Campaign
            val name     =  nameET.text.toString()
            val currentDateAndTime: String = SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
            characterViewModel.addCharacter(Character(
                rolledRace.uid,
                name,
                campaign.uid,
                rolledTemper,
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


}
