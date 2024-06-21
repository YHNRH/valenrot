package com.example.myapplication.ui.interfaces

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.dialog.DeleteDialogFragment
import com.example.myapplication.viewmodel.BaseViewModel

abstract class AbstractListFragment<T>:Fragment(), DeleteEntityInterface<T>, DialogListener {
    var entityToDelete: T? = null
    lateinit var viewModel: BaseViewModel<T>

    override fun onDeleteClick(entity: T){
        entityToDelete = entity
        DeleteDialogFragment<T>().setListener(this).setEntity(entity).show(parentFragmentManager, "DELETE_DIALOG")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        entityToDelete?.let {
            viewModel.delete(it)
            Toast.makeText(requireContext(), "$entityToDelete Deleted", Toast.LENGTH_LONG)
                .show()
        }

    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {}
}