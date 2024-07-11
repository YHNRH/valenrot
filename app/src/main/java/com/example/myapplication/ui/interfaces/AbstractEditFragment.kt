package com.example.myapplication.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.core.common.Consts
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.viewmodel.BaseViewModel

abstract class AbstractEditFragment<T> : Fragment() {
    lateinit var viewModel: BaseViewModel<T>
    protected var entityId:Long = Long.MIN_VALUE

    //region VIEWS
    protected lateinit var saveBtn: Button
    protected lateinit var nameET: EditText
    protected lateinit var textET: EditText
    protected lateinit var isList: CheckBox
    //endregion

    abstract fun refresh(entity : BaseEntity?)

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (requireActivity() as MainActivity).toFragment(Consts.FragmentTags.CAMPAIGNLIST_FRAGMENT)
                }
            })
        val fragment = inflater.inflate(R.layout.fragment_edit, container, false)
        //region VIEWS
        saveBtn = fragment.findViewById(R.id.save)
        nameET = fragment.findViewById(R.id.name)
        textET = fragment.findViewById(R.id.text)
        isList = fragment.findViewById(R.id.isList)
        //endregion
        initViewModel()
        saveBtn.setOnClickListener { saveBtn() }
        return fragment
    }

    abstract fun initViewModel()

    abstract fun saveBtn()

}