package com.fadybassem.elabda3task.ui.dialouges

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.fadybassem.elabda3task.R
import com.fadybassem.elabda3task.ui.interfaces.DialogClickInterface
import com.fadybassem.elabda3task.utils.ErrorCodes

class CustomAlertDialog : DialogClickInterface, DialogInterface.OnClickListener {

    companion object {

        var mDialog: CustomAlertDialog? = null
        lateinit var mDialogClickInterface: DialogClickInterface
        private var mDialogIdentifier: Int = 0
        private var mContext: Context? = null
        internal lateinit var dialog: Dialog

        fun getInstance(): CustomAlertDialog? {
            if (mDialog == null)
                mDialog = CustomAlertDialog()

            return mDialog
        }
    }

    fun showConfirmDialog(
        pTitle: String,
        pMessage: String,
        pPositiveButton: String,
        pNegativeButton: String,
        pContext: Context,
        pDialogIdentifier: Int,
        cancelable: Boolean
    ) {
        mDialogClickInterface = pContext as DialogClickInterface
        mDialogIdentifier = pDialogIdentifier
        mContext = pContext

        dialog = Dialog(pContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alertdialog)
        //dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (pTitle != "") {
            val title: TextView = dialog.findViewById(R.id.textTitle)
            title.text = pTitle
            title.visibility = View.VISIBLE
        }

        val text: TextView = dialog.findViewById(R.id.textDialog)
        text.text = pMessage
        val button: Button = dialog.findViewById(R.id.button) as Button
        button.text = pPositiveButton
        val button1: Button = dialog.findViewById(R.id.button1) as Button
        button1.text = pNegativeButton
        dialog.setCancelable(cancelable)
        dialog.show()

        // if decline button is clicked, close the custom dialog
        button.setOnClickListener { v ->
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier)
        }
        button1.setOnClickListener { v ->
            // Close dialog
            mDialogClickInterface.onClickNegativeButton(dialog, pDialogIdentifier)
        }
        dialog.setOnKeyListener { arg0, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss()
                dialog.setCancelable(true)
            }

            true
        }
    }

    fun showInfoDialog(
        pTitle: String,
        pMessage: String,
        pPositiveButton: String,
        pContext: Context,
        pDialogIdentifier: Int,
        cancelable: Boolean
    ) {
        mDialogClickInterface = pContext as DialogClickInterface
        mDialogIdentifier = pDialogIdentifier
        mContext = pContext

        dialog = Dialog(pContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alertdialog_info)
        //dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (pTitle != "") {
            val title: TextView = dialog.findViewById(R.id.textTitle)
            title.text = pTitle
            title.visibility = View.VISIBLE
        }

        val text: TextView = dialog.findViewById(R.id.textDialog)
        text.text = pMessage
        val button: Button = dialog.findViewById(R.id.button)
        button.text = pPositiveButton
        dialog.setCancelable(cancelable)
        dialog.show()
        // if decline button is clicked, close the custom dialog
        button.setOnClickListener { v ->
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier)
        }

        dialog.setOnKeyListener { arg0, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_BACK) {

                dialog.dismiss()
                dialog.setCancelable(true)
            }

            true
        }
    }

    fun showErrDialog(
        pContext: Context,
        errCode: String,
        pDialogIdentifier: Int,
        cancelable: Boolean
    ) {
        mDialogClickInterface = pContext as DialogClickInterface
        mDialogIdentifier = pDialogIdentifier
        mContext = pContext

        dialog = Dialog(pContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alertdialog_info)
        //dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val title: TextView = dialog.findViewById(R.id.textTitle)
        title.text = pContext.getString(R.string.error)

        val body = dialog.findViewById<TextView>(R.id.textDialog)

        when (errCode) {
            ErrorCodes.ERR_RESPONSE_ERROR -> body.text =
                pContext.getString(R.string.connection_err) + "\n" + errCode
            ErrorCodes.ERR_NETWORK_FAILURE -> body.text =
                pContext.getString(R.string.NoInternetConnection) + "\n" + errCode
            ErrorCodes.ERR_CONVERSION_ISSUE -> body.text =
                pContext.getString(R.string.unknown_error) + "\n" + errCode
            else -> body.text = pContext.getString(R.string.connection_err) + "\n" + errCode
        }

        val button: Button = dialog.findViewById(R.id.button)
        button.text = pContext.getString(R.string.close)

        dialog.setCancelable(cancelable)
        dialog.show()

        // if decline button is clicked, close the custom dialog
        button.setOnClickListener { v ->
            // Close dialog
            mDialogClickInterface.onClickPositiveButton(dialog, pDialogIdentifier)
        }

        dialog.setOnKeyListener { arg0, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_BACK) {

                dialog.dismiss()
                dialog.setCancelable(true)
            }

            true
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                mDialogClickInterface.onClickPositiveButton(dialog, which)
            }

            DialogInterface.BUTTON_NEGATIVE -> {
                mDialogClickInterface.onClickNegativeButton(dialog, which)
            }
        }
    }

    override fun onClickPositiveButton(pDialog: DialogInterface?, pDialogIntefier: Int) {

    }

    override fun onClickNegativeButton(pDialog: DialogInterface?, pDialogIntefier: Int) {
        dialog.dismiss()
    }

}