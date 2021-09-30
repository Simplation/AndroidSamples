package com.simplation.animator

import android.R
import android.animation.Animator
import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import com.simplation.animator.databinding.ActivityOtherBinding
import android.graphics.drawable.Drawable
import android.os.Build

import androidx.annotation.RequiresApi


class OtherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherBinding
    private var isTwitterChecked = false
    private var isSearchChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*// window.enterTransition = Explode()
        // window.enterTransition = Slide()
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Fade()
        window.exitTransition = Fade()*/
        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnim()

        /**
         * animated-selector 用法步骤
         *  1. 首先定义一个 animated-selector，它定义两个 item，对应两种状态 on、off 的显示，再定义两个 transition 用于状态变化时启动动画。
         *  2. 两个 item 是 vector 类型，定义要显示的 path。
         *  3. 两个 transition 是 animated-vector 类型，定义从一个状态到另一个状态时的动画，在指定动画时要注意，一个对象上只能加载一个动画，如果动画个数比对象个数多，要用 group 把对象包裹起来。
         */
        startAnimation()

        startTrimAnimation()
    }

    private fun startTrimAnimation() {
        binding.imageView7.setOnClickListener {
            isSearchChecked = !isSearchChecked
            val state = intArrayOf(R.attr.state_checked * if (isSearchChecked) 1 else -1)
            binding.imageView7.setImageState(state, true)
        }
    }

    private fun startAnimation() {
        binding.imageView6.setOnClickListener {
            isTwitterChecked = !isTwitterChecked
            val stateSet = intArrayOf(R.attr.state_checked * if (isTwitterChecked) 1 else -1)
            binding.imageView6.setImageState(stateSet, true)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun startAnim() {
        val drawable: Drawable = binding.imageView5.drawable
        (drawable as Animatable).start()
    }
}
