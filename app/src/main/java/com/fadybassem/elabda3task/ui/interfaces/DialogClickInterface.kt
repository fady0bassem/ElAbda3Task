package com.fadybassem.elabda3task.ui.interfaces

import android.content.DialogInterface

interface DialogClickInterface {

    fun onClickPositiveButton(pDialog: DialogInterface?, pDialogIntefier: Int)

    fun onClickNegativeButton(pDialog: DialogInterface?, pDialogIntefier: Int)
}