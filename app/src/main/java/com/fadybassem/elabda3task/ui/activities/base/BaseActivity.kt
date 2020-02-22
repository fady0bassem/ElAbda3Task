package com.fadybassem.elabda3task.ui.activities.base

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.fadybassem.elabda3task.R

abstract class BaseActivity : AppCompatActivity() {

    val TAG = BaseActivity::class.java.name
    private var base_linearlayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onViewReady(savedInstanceState, intent)
    }

    @CallSuper
    open fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

    }
}