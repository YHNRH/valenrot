package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.core.common.Consts
import com.example.myapplication.core.common.Consts.FragmentTags.CAMPAIGNEDIT_FRAGMENT
import com.example.myapplication.core.common.Consts.FragmentTags.CAMPAIGNLIST_FRAGMENT
import com.example.myapplication.core.common.Consts.FragmentTags.INSTRUCTION_FRAGMENT
import com.example.myapplication.core.common.Consts.FragmentTags.MAIN_FRAGMENT
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.campaign.CampaignFragment
import com.example.myapplication.ui.campaign.CampaignListFragment
import com.example.myapplication.ui.instruction.SectionFragment
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.ui.roll.RollFragment
import com.example.myapplication.ui.instruction.InstructionFragment

class MainActivity : AppCompatActivity() {
    private var rollFragment = RollFragment()
    private var campaignListFragment = CampaignListFragment()
    private var campaignFragment = CampaignFragment()
    private var instructionFragment = InstructionFragment()
    private var sectionFragment = SectionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.menu_roll).setOnClickListener{ toFragment(MAIN_FRAGMENT) }
        findViewById<TextView>(R.id.menu_instruction).setOnClickListener {toFragment(INSTRUCTION_FRAGMENT)}
        findViewById<TextView>(R.id.menu_campaigns).setOnClickListener {toFragment(CAMPAIGNLIST_FRAGMENT)}

        if (savedInstanceState == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.add(R.id.container, rollFragment, MAIN_FRAGMENT.toString())
            fragmentTransaction.commit()
        }
    }

    fun toFragment(tag: Consts.FragmentTag){
        val fr = getSufficientFragment(tag)
        val fragment = supportFragmentManager
            .findFragmentByTag(tag.toString())

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                fr,
                tag.toString()
            )
            fragmentTransaction.commit()
            changeMenuButtonColor(getMenuButton(tag))
        }
    }
    private fun changeMenuButtonColor(v:View?){
        if (v !== null) {
            (v.parent as LinearLayout).children.forEach { v ->
                v.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
            }
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
        }
    }
    fun toEditFragment(entity : BaseEntity){
        val tag = getSufficientFragmentTag(entity)
        val fr = getSufficientFragment(entity)
        val fragment = supportFragmentManager
            .findFragmentByTag(tag)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                fr,
                tag
            )
            fragmentTransaction.commitNow()
            fr.refresh(entity)
        }
    }

    private fun getSufficientFragmentTag(entity : BaseEntity): String {
        return when (entity){
            is Campaign -> CAMPAIGNEDIT_FRAGMENT.toString()
            else -> ""
        }
    }

    private fun getSufficientFragment(entity : BaseEntity): AbstractEditFragment<*> {
        return when (entity){
            is Campaign -> campaignFragment
            is Section -> sectionFragment
            else -> campaignFragment
        }
    }

    private fun getSufficientFragment(tag : Consts.FragmentTag): Fragment {
        return when (tag){
            MAIN_FRAGMENT -> rollFragment
            INSTRUCTION_FRAGMENT -> instructionFragment
            CAMPAIGNLIST_FRAGMENT -> campaignListFragment
            else -> rollFragment
        }
    }

    private fun getMenuButton(tag : Consts.FragmentTag) : View {
        return when (tag){
            MAIN_FRAGMENT -> findViewById(R.id.menu_roll)
            INSTRUCTION_FRAGMENT -> findViewById(R.id.menu_instruction)
            CAMPAIGNLIST_FRAGMENT -> findViewById(R.id.menu_campaigns)
            else -> findViewById(R.id.menu_roll)
        }
    }
}