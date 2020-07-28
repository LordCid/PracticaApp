package com.slashmobility.seleccion.albert.cid.presentation.common

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.slashmobility.seleccion.albert.cid.R

class ErrorDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.download_error_message)
                .setNegativeButton(R.string.ok_label) { _, _ -> dismiss() }
           return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}