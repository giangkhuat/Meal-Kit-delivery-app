package com.ait.mealkitdeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH : Long = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        var fadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_demo)
        var fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        splashscreen.startAnimation(fadeOut)

        fadeOut.setAnimationListener(
            object: Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    logoImg.visibility = View.VISIBLE
                    logoImg.startAnimation(fadeIn)
                }

                override fun onAnimationStart(p0: Animation?) {

                }
            }
        )
        fadeIn.setAnimationListener(
            object: Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    val mainIntent = Intent(this@SplashActivity, ExploreActivity::class.java)

                    this@SplashActivity.startActivity(mainIntent)
                    this@SplashActivity.finish()
                }

                override fun onAnimationStart(p0: Animation?) {

                }
            }
        )

    }
}
