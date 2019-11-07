package com.example.imgtextspan

import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val redColor = Color.parseColor("#ff0000")
    private val whiteColor = Color.parseColor("#ffffff")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTextSpan1()
        initTextSpan2()
        initTextSpan3()
        initTextSpan4()
    }


    private fun initTextSpan1() {
        val spannableString = SpannableString("a西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州")
        val tagSpan2 = ImgTextSpan(this, Paint.Style.STROKE, redColor, redColor, sp2px(16f), 3, 10, 10, 10)
        spannableString.setSpan(tagSpan2, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_span1.text = spannableString
    }

    private fun initTextSpan2() {
        val spannableString = SpannableString("A西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州")
        val tagSpan2 = ImgTextSpan(this, Paint.Style.FILL, whiteColor, redColor, sp2px(16f), 3, 10, 10, 10)
        spannableString.setSpan(tagSpan2, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_span2.text = spannableString
    }

    private fun initTextSpan3() {
        val spannableString = SpannableString("b西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州")
        val tagSpan2 = ImgTextSpan(
            this,
            Paint.Style.STROKE,
            redColor,
            redColor,
            sp2px(16f),
            3,
            10,
            10,
            10,
            20,
            20
        )
        spannableString.setSpan(tagSpan2, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_span3.text = spannableString
    }

    private fun initTextSpan4() {
        val spannableString = SpannableString("西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州西安北京重庆青岛天津上海广州")
        val tagSpan2 = ImgTextSpan(
            this,
            "https://b-ssl.duitang.com/uploads/blog/201312/04/20131204184148_hhXUT.jpeg",
            tv_span4,
            Paint.Style.FILL,
            whiteColor,
            redColor,
            sp2px(16f),
            10,
            10,
            10,
            10,
            20,
            20
        )
        spannableString.setSpan(tagSpan2, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_span4.text = spannableString
    }


    private fun sp2px(spValue: Float): Int {
        return Math.round(spValue * resources.displayMetrics.scaledDensity)
    }
}
