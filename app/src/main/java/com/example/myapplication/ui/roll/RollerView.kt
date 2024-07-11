package com.example.myapplication.ui.roll

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.core.common.observeOnce
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.campaign.CampaignSpinnerAdapter
import com.example.myapplication.viewmodel.SectionViewModel
import com.google.android.flexbox.FlexboxLayout
import kotlin.random.Random

class RollerView : FrameLayout {
    private lateinit var vm: SectionViewModel
    private lateinit var fragment: Fragment
    private lateinit var adapter:CampaignSpinnerAdapter<Section>
    private lateinit var spinner:Spinner
    private lateinit var resultNumber:TextView
    private lateinit var resultText:TextView
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        initView()
    }

    constructor(context: Context?, fragment: Fragment, vm: SectionViewModel) : super(context!!) {
        this.fragment = fragment
        this.vm = vm
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.fragment_roller, this)

        spinner = findViewById(R.id.spinner)
        resultNumber = findViewById<TextView>(R.id.result_number)
        resultText = findViewById<TextView>(R.id.result_text)

        adapter = CampaignSpinnerAdapter(context)
        spinner.adapter = adapter
        vm.allEntities.observe(fragment) { list ->
            list?.let {
                adapter.updateList(it.filter {
                    it.parentId == null && it.isList
                })
            }
        }
        setOnLongClickListener{
            (this.parent as FlexboxLayout).removeView(it)
            true
        }
    }

    fun roll(){
        val imageView = findViewById<ImageView>(R.id.myImageView)
        resultNumber.text = ""
        resultText.text = ""

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
                            roll1()
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
            .into(imageView)
    }

    fun roll1(){
        vm.allEntities.observeOnce(fragment){
            val list = it.filter { it.parentId == (spinner.selectedItem as Section).uid }
            val rnd = Random.nextInt(list.size)
            resultNumber.text = (rnd + 1).toString()
            resultText.text = list[rnd].title
        }
    }
}