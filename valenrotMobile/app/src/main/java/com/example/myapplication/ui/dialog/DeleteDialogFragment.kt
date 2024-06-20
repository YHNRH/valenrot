package com.example.myapplication.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.core.room.entity.BaseEntity

class DeleteDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_delete, null)
            view.findViewById<TextView>(R.id.text).text = "Вы действительно хотите удалить ${entity.name}?"
            builder.setView(view)
                .setPositiveButton("Подтвердить"
                ) { dialog, id ->
                    listener.onDialogPositiveClick(this)
                }
                .setNegativeButton("Отмена"
                ) { dialog, id ->
                    listener.onDialogNegativeClick(this)
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    internal lateinit var listener: DialogListener

    interface DialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    fun setListener(listener: Fragment): DeleteDialogFragment {
        try {
            this.listener = listener as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((listener.toString() +
                    " must implement NoticeDialogListener"))
        }
        return this
    }

    lateinit var entity:BaseEntity

    fun setEntity(entity: BaseEntity): DeleteDialogFragment {
        this.entity = entity
        return this
    }
}
