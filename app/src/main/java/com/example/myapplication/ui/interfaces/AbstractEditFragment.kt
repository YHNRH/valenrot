package com.example.myapplication.ui.interfaces

import androidx.fragment.app.Fragment
import com.example.myapplication.core.room.entity.BaseEntity

abstract class AbstractEditFragment : Fragment() {
    abstract fun refresh(entity : BaseEntity?)
}