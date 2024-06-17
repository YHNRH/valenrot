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
import androidx.fragment.app.viewModels
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
import com.example.myapplication.ui.campaignlist.CampaignSpinnerAdapter
import com.example.myapplication.viewmodel.CampaignViewModel
import com.example.myapplication.viewmodel.CharacterViewModel
import com.example.myapplication.viewmodel.RollViewModel
import java.text.SimpleDateFormat
import java.util.Date


class RollFragment : Fragment() {

    private val viewModel: RollViewModel by viewModels()
    lateinit var campaignViewModel: CampaignViewModel
    lateinit var characterViewModel: CharacterViewModel
    lateinit var spinner:Spinner
    lateinit var nameET:EditText
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
        fragment.findViewById<Button>(R.id.save_btn).setOnClickListener {view -> save(view)}
        nameET = fragment.findViewById(R.id.name)

        campaignViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CampaignViewModel::class.java]

        characterViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CharacterViewModel::class.java]

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
//                    Toast.makeText(requireContext(),
//                        "Selected item" + " " +
//                                "" + languages[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        return fragment
    }

    private fun roll(view: View) {

        val root = view.rootView
        val textView = root.findViewById<TextView>(R.id.roll_tw)
        val textView1 = root.findViewById<TextView>(R.id.roll_tw1)
        textView.text = ""
        textView1.text = ""
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
                            textView.text = viewModel.roll().toString()
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
                            textView1.text = viewModel.roll().toString()
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
    private fun save(view: View) {
        val campaign = spinner.selectedItem as Campaign
        val name =nameET.text.toString()
        val currentDateAndTime: String = SimpleDateFormat("dd MMM, yyyy - HH:mm").format(Date())
        Toast.makeText(requireContext(),
            campaign.name + "\n" +
            campaign.uid + "\n" +
            name + "\n"

            , Toast.LENGTH_SHORT).show()

        characterViewModel.addCharacter(Character(
            1,
            name,
            campaign.uid,
            currentDateAndTime
        ))
    }


}