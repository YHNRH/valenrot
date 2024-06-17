package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.core.room.entity.Campaign
import com.example.myapplication.core.room.entity.Race
import com.example.myapplication.ui.campaign.CampaignFragment
import com.example.myapplication.ui.campaignlist.CampaignListFragment
import com.example.myapplication.ui.roll.RollFragment
import com.example.myapplication.ui.race.RaceFragment
import com.example.myapplication.ui.racelist.RaceListFragment

class MainActivity : AppCompatActivity() {
    private var rollFragment:RollFragment = RollFragment()
    private var raceListFragment: RaceListFragment = RaceListFragment()
    private var campaignListFragment: CampaignListFragment = CampaignListFragment()
    private var raceFragment:RaceFragment = RaceFragment()
    private var campaignFragment:CampaignFragment = CampaignFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.menu_roll).setOnClickListener{toRollFragment()}
        findViewById<TextView>(R.id.menu_races).setOnClickListener {toRaceListFragment()}
        findViewById<TextView>(R.id.menu_campaigns).setOnClickListener {toCampaignListFragment()}

        if (savedInstanceState == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.add(R.id.container, rollFragment, MAIN_FRAGMENT)
            fragmentTransaction.commit()
        }
    }

    fun toRollFragment (){
        val fragment = supportFragmentManager
            .findFragmentByTag(MAIN_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                rollFragment,
                MAIN_FRAGMENT
            )
            fragmentTransaction.commit()
        }
    }

    fun toRaceListFragment(){
        val fragment = supportFragmentManager
            .findFragmentByTag(RACELIST_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                raceListFragment,
                RACELIST_FRAGMENT
            )
            fragmentTransaction.commit()
        }
    }
    fun toCampaignListFragment(){
        val fragment = supportFragmentManager
            .findFragmentByTag(CAMPAIGNLIST_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                campaignListFragment,
                CAMPAIGNLIST_FRAGMENT
            )
            fragmentTransaction.commit()
        }
    }
    fun toRaceEditFragment(race: Race){
        val fragment = supportFragmentManager
            .findFragmentByTag(RACEEDIT_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                raceFragment,
                RACEEDIT_FRAGMENT
            )
            fragmentTransaction.commitNow()
            raceFragment.refresh(race)
        }
    }

    fun toCampaignEditFragment(campaign: Campaign){
        val fragment = supportFragmentManager
            .findFragmentByTag(CAMPAIGNEDIT_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                campaignFragment,
                CAMPAIGNEDIT_FRAGMENT
            )
            fragmentTransaction.commitNow()
            campaignFragment.refresh(campaign)
        }
    }

    companion object {
        const val MAIN_FRAGMENT: String = "MAIN_FRAGMENT"
        const val RACELIST_FRAGMENT: String = "RACELIST_FRAGMENT"
        const val RACEEDIT_FRAGMENT: String = "RACEEDIT_FRAGMENT"
        const val CAMPAIGNLIST_FRAGMENT: String = "CAMPAIGNLIST_FRAGMENT"
        const val CAMPAIGNEDIT_FRAGMENT: String = "CAMPAIGNEDIT_FRAGMENT"
    }
}