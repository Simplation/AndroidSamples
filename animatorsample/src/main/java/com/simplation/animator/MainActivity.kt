package com.simplation.animator

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.simplation.animator.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Build
import android.app.ActivityOptions
import android.util.Pair


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tween 动画：通过 AnimationUtils 来使用
        /*val animator = AnimationUtils.loadAnimation(this, R.anim.tween_sample)
        binding.imageView.animation = animator*/

        // 属性动画
        //region Sample 1
        val valueAnimator1 = ValueAnimator.ofFloat(0F, 360F)
        valueAnimator1.duration = 1000
        valueAnimator1.repeatCount = 1
        valueAnimator1.repeatMode = ValueAnimator.REVERSE
        valueAnimator1.interpolator = AccelerateInterpolator()
        valueAnimator1.addUpdateListener {
            // 当前动画进度百分比，范围是 0-1
            val fraction = it.animatedFraction
            // 当前动画值，范围是 ofFloat 的参数
            val value = it.animatedValue as Float
            binding.imageView.rotationY = value
            Log.d("--- === ---", "onCreate: $fraction")
        }
        valueAnimator1.start()
        //endregion

        //region Sample 2：TextView 动态显示金额
        val valueAnimator2 = ValueAnimator.ofFloat(0F, 2000F)
        valueAnimator2.duration = 1000
        valueAnimator2.addUpdateListener {
            val value = it.animatedValue as Float
            binding.textView.text = value.toString()
        }
        valueAnimator2.start()
        //endregion

        //region Sample 3 ObjectAnimator
        /**
         * propertyName 可以指定不同的动画
         *      平移： translationX
         *      缩放： scaleX
         *      旋转： rotationX
         *      透明度：alpha
         */
        val animator: ObjectAnimator =
            ObjectAnimator.ofFloat(binding.imageView2, "rotationY", 0F, 359F)
        animator.duration = 1000
        animator.repeatCount = 1
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
        //endregion

        //region Sample 3 Path 动画
        val path = Path()
        path.lineTo(0F, 0F)
        path.lineTo(300F, 500F)
        path.lineTo(600F, 100F)
        /**
         * ObjectAnimator.ofFloat()
         *      参数 1：设置动画的控件
         *      参数 2：设置动画的方向
         *      参数 3：设置动画的方向
         *      参数 4：动画的 path 路径
         */
        val animator1 = ObjectAnimator.ofFloat(binding.button, View.X, View.Y, path)
        animator1.duration = 2000
        animator1.repeatCount = 1
        animator1.repeatMode = ObjectAnimator.REVERSE
        animator1.start()
        //endregion

        //region Sample 4 关键帧动画
        binding.imageView3.setImageResource(R.drawable.animator_list)
        val animator2 = binding.imageView3.drawable as AnimationDrawable
        animator2.start()
        //endregion

        //region Sample 5 createCircularReveal
        /**
         * createCircularReveal
         *
         * view – The View will be clipped to the animating circle.
         * centerX – The x coordinate of the center of the animating circle, relative to view.
         * centerY – The y coordinate of the center of the animating circle, relative to view.
         * startRadius – The starting radius of the animating circle.
         * endRadius – The ending radius of the animating circle.
         */
        binding.imageView4.post {
            binding.imageView4.createCircularReveal(
                0,
                0,
                30f,
                binding.imageView4.measuredWidth.toFloat()
            )
        }
        //endregion


        // 传统的转场动画
        binding.button2.setOnClickListener {
            Log.d("--===--", "onCreate: ------")

            startActivity(Intent(this, OtherActivity::class.java))
            // 调佣系统的动画
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        // Android 5.0 转场动画
        binding.button4.setOnClickListener {
            // Explode, 目标 Activity 需要设置对应的 window.enterTransition = Explode()
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        binding.button5.setOnClickListener {
            // Slide, 目标 Activity 需要设置对应的 window.enterTransition = Slide()
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        binding.button6.setOnClickListener {
            // Fade, 目标 Activity 需要设置对应的 window.enterTransition = Fade()
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        binding.button7.setOnClickListener {
            // Share，比较复杂
            // 两个 Activity 共享的元素需要设置相同的 transitionName： android:transitionName="fab"
            // 跳转时，要为每一个共享的 view 设置对应的 transitionName
            // 跳转的 Activity 在 onCreate 方法中开启 Transition 模式
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(binding.button7, "button7"),
                    Pair.create(binding.button6, "button6")
                ).toBundle()
            )
        }
    }

    private fun View.createCircularReveal(
        centerX: Int,
        centerY: Int,
        startRadius: Float,
        endRadius: Float
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            visibility = View.GONE
            val anim = ViewAnimationUtils.createCircularReveal(
                this,
                centerX,
                centerY,
                startRadius,
                endRadius
            )
            anim.duration = 1500
            visibility = View.VISIBLE
            anim.start()
        } else {
            visibility = View.VISIBLE
        }
    }

}