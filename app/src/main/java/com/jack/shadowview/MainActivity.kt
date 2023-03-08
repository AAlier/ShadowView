package com.jack.shadowview

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: TextView
    private lateinit var shadowView1: ShadowView
    private lateinit var shadowView2: ShadowView
    private lateinit var radiusSliderView: Slider
    private lateinit var alphaSliderView: Slider
    private lateinit var radiusTextView: TextView
    private lateinit var shadowWidthSliderView: Slider
    private lateinit var shadowWidthTextView: TextView
    private lateinit var alphaTextView: TextView
    private lateinit var shadowXSliderView: Slider
    private lateinit var shadowYSliderView: Slider
    private lateinit var shadowXTextView: TextView
    private lateinit var shadowYTextView: TextView

    private lateinit var shadowColorView: View
    private lateinit var backgroundColorView: View

    private var backgroundColor: Int = Color.CYAN
    private var shadowColor: Int = Color.RED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        bindListeners()
        onValueChanged()
    }

    private fun bindViews() {
        imageView = findViewById<TextView>(R.id.imageView)
        shadowView1 = findViewById<ShadowView>(R.id.shadowView1)
        shadowView2 = findViewById<ShadowView>(R.id.shadowView2)

        shadowXSliderView = findViewById<Slider>(R.id.shadowXSliderView)
        shadowXTextView = findViewById<TextView>(R.id.shadowXTextView)
        shadowYSliderView = findViewById<Slider>(R.id.shadowYSliderView)
        shadowYTextView = findViewById<TextView>(R.id.shadowYTextView)
        shadowWidthSliderView = findViewById<Slider>(R.id.shadowWidthSliderView)
        shadowWidthTextView = findViewById<TextView>(R.id.shadowWidthTextView)
        radiusSliderView = findViewById<Slider>(R.id.radiusSliderView)
        radiusTextView = findViewById<TextView>(R.id.radiusTextView)
        alphaSliderView = findViewById<Slider>(R.id.alphaSliderView)
        alphaTextView = findViewById<TextView>(R.id.alphaTextView)
        shadowColorView = findViewById<View>(R.id.shadowColor)
        backgroundColorView = findViewById<View>(R.id.viewColor)
    }

    private fun bindListeners() {
        radiusSliderView.addOnChangeListener { slider, value, fromUser ->
            onValueChanged()
        }
        alphaSliderView.addOnChangeListener { slider, value, fromUser ->
            onValueChanged()
        }
        shadowWidthSliderView.addOnChangeListener { slider, value, fromUser ->
            onValueChanged()
        }
        shadowXSliderView.addOnChangeListener { slider, value, fromUser ->
            onValueChanged()
        }
        shadowYSliderView.addOnChangeListener { slider, value, fromUser ->
            onValueChanged()
        }
        shadowColorView.setOnClickListener {
            ColorPickerDialog(
                this,
                colorPickerResultListener,
                "Shadow",
                shadowColor,
                Color.RED
            ).show()
        }
        backgroundColorView.setOnClickListener {
            ColorPickerDialog(
                this,
                colorPickerResultListener,
                "Background",
                backgroundColor,
                Color.RED
            ).show()
        }
    }

    private fun onValueChanged() {
        imageView.background = ShadowDrawable.Builder()
            .setBackgroundColor(backgroundColor)
            .setShadowColor(shadowColor)
            .setRadius(radiusSliderView.value)
            .setAlpha(alphaSliderView.value)
            .setX(shadowXSliderView.value)
            .setY(shadowYSliderView.value)
            .setShadowWidth(shadowWidthSliderView.value)
            .build()
        radiusTextView.text = "${radiusSliderView.value.toInt()}"
        alphaTextView.text = String.format("%.2f", alphaSliderView.value)
        shadowWidthTextView.text = "${shadowWidthSliderView.value.toInt()}"
        shadowXTextView.text = "${shadowXSliderView.value.toInt()}"
        shadowWidthTextView.text = "${shadowWidthSliderView.value.toInt()}"
        shadowView1.setShadowColor(
            backgroundColor,
            shadowColor,
            alphaSliderView.value,
            shadowXSliderView.value,
            shadowYSliderView.value,
            radiusSliderView.value,
            shadowWidthSliderView.value
        )
        shadowView2.setShadowColor(
            backgroundColor,
            shadowColor,
            alphaSliderView.value,
            shadowXSliderView.value,
            shadowYSliderView.value,
            radiusSliderView.value,
            shadowWidthSliderView.value
        )
        shadowColorView.backgroundTintList = ColorStateList.valueOf(shadowColor)
        backgroundColorView.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    }

    private val colorPickerResultListener = ColorPickerDialog.OnColorChangedListener { key, color ->
        if (key == "Shadow") {
            shadowColor = color
        } else {
            backgroundColor = color
        }
        onValueChanged()
    }
}