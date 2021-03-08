package com.dutra.patricio.desafiophi.util

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import com.dutra.patricio.desafiophi.R

class Alerta {

    companion object {

        lateinit var dialog: Dialog

        @JvmStatic
        fun aviso(msg: String, act: Activity) {

            val builder = AlertDialog.Builder(act)
            builder.setTitle(R.string.Warning)
            builder.setMessage(msg)
            builder.setPositiveButton(R.string.Continue, DialogInterface.OnClickListener { dialog, which ->  dialog.dismiss()})
            builder.setCancelable(true)
            dialog = builder.create()
            dialog.show()

        }
    }
}