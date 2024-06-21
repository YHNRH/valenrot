package com.example.myapplication.ui.interfaces

import androidx.fragment.app.DialogFragment

interface DialogListener {
    fun onDialogPositiveClick(dialog: DialogFragment)
    fun onDialogNegativeClick(dialog: DialogFragment)
}