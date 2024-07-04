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
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.core.room.entity.Subrace
import com.example.myapplication.ui.campaign.CampaignFragment
import com.example.myapplication.ui.campaignlist.CampaignListFragment
import com.example.myapplication.ui.interfaces.AbstractEditFragment
import com.example.myapplication.ui.roll.RollFragment
import com.example.myapplication.ui.race.RaceFragment
import com.example.myapplication.ui.subrace.SubraceFragment
import com.example.myapplication.ui.racelist.RaceListFragment

class MainActivity : AppCompatActivity() {
    private var rollFragment = RollFragment()
    private var raceListFragment = RaceListFragment()
    private var campaignListFragment = CampaignListFragment()
    private var raceFragment = RaceFragment()
    private var subraceFragment = SubraceFragment()
    private var campaignFragment = CampaignFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.menu_roll).setOnClickListener{v -> toFragment(v)}
        findViewById<TextView>(R.id.menu_races).setOnClickListener {v -> toFragment(v)}
        findViewById<TextView>(R.id.menu_campaigns).setOnClickListener {v -> toFragment(v)}

        if (savedInstanceState == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.add(R.id.container, rollFragment, MAIN_FRAGMENT)
            fragmentTransaction.commit()
        }
    }

    private fun toFragment(v:View){
        val tag = getSufficientFragmentTag(v as TextView)
        val fr = getSufficientFragment(v)
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
            fragmentTransaction.commit()
            changeMenuButtonColor(v)
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
            is Subrace -> SUBRACEEDIT_FRAGMENT
            is Race -> RACEEDIT_FRAGMENT
            is Campaign -> CAMPAIGNEDIT_FRAGMENT
            else -> ""
        }
    }

    private fun getSufficientFragmentTag(view:TextView): String {
        return when (view.text){
            "Главная" -> MAIN_FRAGMENT
            "Расы" -> RACELIST_FRAGMENT
            "Кампании" -> CAMPAIGNLIST_FRAGMENT
            else -> ""
        }
    }

    private fun getSufficientFragment(entity : BaseEntity): AbstractEditFragment {
        return when (entity){
            is Subrace -> subraceFragment
            is Campaign -> campaignFragment
            is Race -> raceFragment
            else -> raceFragment
        }
    }

    private fun getSufficientFragment(view : TextView): Fragment {
        return when (view.text){
            "Главная" -> rollFragment
            "Расы" -> raceListFragment
            "Кампании" -> campaignListFragment
            else -> rollFragment
        }
    }

    companion object {
        const val MAIN_FRAGMENT: String = "MAIN_FRAGMENT"
        const val RACELIST_FRAGMENT: String = "RACELIST_FRAGMENT"
        const val RACEEDIT_FRAGMENT: String = "RACEEDIT_FRAGMENT"
        const val SUBRACEEDIT_FRAGMENT: String = "SUBRACEEDIT_FRAGMENT"
        const val CAMPAIGNLIST_FRAGMENT: String = "CAMPAIGNLIST_FRAGMENT"
        const val CAMPAIGNEDIT_FRAGMENT: String = "CAMPAIGNEDIT_FRAGMENT"
    }
}