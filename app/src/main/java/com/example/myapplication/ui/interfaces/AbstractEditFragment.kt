package com.example.myapplication.ui.interfaces

import androidx.fragment.app.Fragment
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.viewmodel.BaseViewModel

abstract class AbstractEditFragment<T> : Fragment() {
    lateinit var viewModel: BaseViewModel<T>
    abstract fun refresh(entity : BaseEntity?)
}