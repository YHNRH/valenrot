package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.ui.main.MainFragment
import com.example.myapplication.ui.raceeditor.RaceEditFragment
import com.example.myapplication.ui.racelist.RaceListFragment

class MainActivity : AppCompatActivity() {
    private var mainFragment:MainFragment = MainFragment()
    private var raceListFragment:RaceListFragment = RaceListFragment()
    private var raceEditFragment:RaceEditFragment = RaceEditFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.button1).setOnClickListener{toMainFragment()}

        findViewById<TextView>(R.id.button2).setOnClickListener {toRaceListFragment()}

        if (savedInstanceState == null) {
            // при первом запуске программы
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.container, mainFragment, MAIN_FRAGMENT)
            fragmentTransaction.commit()
        }
    }

    fun toMainFragment (){
        val fragment = supportFragmentManager
            .findFragmentByTag(MAIN_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                mainFragment,
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

    fun toRaceEditFragment(){
        val fragment = supportFragmentManager
            .findFragmentByTag(RACEEDIT_FRAGMENT)

        if (fragment == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                raceEditFragment,
                RACEEDIT_FRAGMENT
            )
            fragmentTransaction.commit()
        }
    }

    companion object {
        const val MAIN_FRAGMENT: String = "MAIN_FRAGMENT"
        const val RACELIST_FRAGMENT: String = "RACELIST_FRAGMENT"
        const val RACEEDIT_FRAGMENT: String = "RACEEDIT_FRAGMENT"
    }
}