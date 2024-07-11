package com.example.myapplication.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.BaseEntity
import com.example.myapplication.core.room.entity.Section
import com.example.myapplication.ui.dialog.DeleteDialogFragment
import com.example.myapplication.viewmodel.BaseViewModel

abstract class AbstractListFragment<T : BaseEntity>:
    Fragment(),
    OnDeleteEntityInterface<T>,
    OnClickEntityInterface<T>,
    DialogListener {
    private var entityToDelete: T? = null
    lateinit var viewModel: BaseViewModel<T>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: AbstractRVAdapter<T>
    lateinit var addBtn: ImageView
    abstract val layout:Int
    override fun onDeleteClick(entity: T){
        entityToDelete = entity
        DeleteDialogFragment<T>().setListener(this).setEntity(entity).show(parentFragmentManager, "DELETE_DIALOG")
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(layout, container, false)
        recyclerView = fragment.findViewById(R.id.list)
        addBtn = fragment.findViewById(R.id.addBtn)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        init()
        viewModel.allEntities.observe(this.requireActivity()) { list ->
            list?.let {
                adapter.updateList(it.filter {
                    if (it is Section)
                        it.parentId == null
                    else
                        true
                })
            }
        }
        recyclerView.adapter = adapter
        return fragment
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        entityToDelete?.let {
            viewModel.delete(it)
            Toast.makeText(requireContext(), "$entityToDelete Deleted", Toast.LENGTH_LONG)
                .show()
        }
    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {}

    abstract fun init()
}