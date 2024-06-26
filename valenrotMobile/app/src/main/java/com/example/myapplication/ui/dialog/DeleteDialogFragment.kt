package com.example.myapplication.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.interfaces.DialogListener

class DeleteDialogFragment<T> : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_delete, null)
            view.findViewById<TextView>(R.id.text).text = "Вы действительно хотите удалить ${entity}?"
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

    private lateinit var listener: DialogListener

    fun setListener(listener: Fragment): DeleteDialogFragment<T> {
        try {
            this.listener = listener as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((listener.toString() +
                    " must implement NoticeDialogListener"))
        }
        return this
    }

    var entity: T? = null

    fun setEntity(entity: T): DeleteDialogFragment<T> {
        this.entity = entity
        return this
    }
}
