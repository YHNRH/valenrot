package com.example.myapplication.core.common

class Consts {
    class FragmentTag(private val tag: String) {
        override fun toString(): String {
            return tag
        }
    }


    object FragmentTags {
        val MAIN_FRAGMENT = FragmentTag("MAIN_FRAGMENT")
        val CAMPAIGNLIST_FRAGMENT = FragmentTag("CAMPAIGNLIST_FRAGMENT")
        val CAMPAIGNEDIT_FRAGMENT= FragmentTag("CAMPAIGNEDIT_FRAGMENT")
        val INSTRUCTION_FRAGMENT= FragmentTag("INSTRUCTION_FRAGMENT")
    }
}