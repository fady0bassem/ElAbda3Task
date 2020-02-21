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
        setContentView(getLayoutResourceId())
        onViewReady(savedInstanceState, intent)
    }

    override fun setContentView(layoutResID: Int) {
        base_linearlayout = layoutInflater.inflate(R.layout.base_activity, null) as LinearLayout

        val base_linearlayout: LinearLayout =
            base_linearlayout!!.findViewById(R.id.base_linearlayout)
        layoutInflater.inflate(layoutResID, base_linearlayout, true)

        super.setContentView(base_linearlayout)
    }

    protected abstract fun getLayoutResourceId(): Int

    @CallSuper
    open fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

    }
}