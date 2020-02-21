package com.fadybassem.elabda3task.ui.dialouges

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.fadybassem.elabda3task.R
import com.github.loadingview.LoadingView

class CustomProgressDialog {

    companion object {

        fun showProgressDialog(context: Context, message: String): ProgressDialog {
            val dialog = ProgressDialog(context)
            dialog.isIndeterminate = true
            dialog.setMessage(message)
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog.setCancelable(false)
            dialog.show()

            return dialog
        }

        fun loadingIndicatorView(context: Context, cancelable: Boolean): Dialog {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.indicator_view)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val loadingView = dialog.findViewById<LoadingView>(R.id.loadingView)
            loadingView.start()

            dialog.setCancelable(false)
            dialog.show()

            return dialog
        }
    }
}