package com.fadybassem.elabda3task.ui.activities.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.fadybassem.elabda3task.R
import com.fadybassem.elabda3task.ui.activities.base.BaseActivity
import com.fadybassem.elabda3task.ui.activities.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private val splash_time = 1000
    private val animation_time: Long = 3000

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_splash
    }

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)

        crossfade()
    }

    private fun crossfade() {
        imageView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(animation_time)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        navigate()
                    }
                })
        }
    }

    private fun navigate() {
        Handler().postDelayed({
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }, splash_time.toLong())
    }
}
