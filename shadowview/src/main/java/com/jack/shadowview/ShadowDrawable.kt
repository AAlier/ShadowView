package com.jack.shadowview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

class ShadowDrawable private constructor(
    private val radius: Float = dipF(8),
    backgroundColor: Int = Color.WHITE,
    shadowColor: Int = Color.RED,
    private val shadowWidth: Float = dipF(8),
    private val shadowAlpha: Float = 0.2f,
    private val dx: Float = 0f,
    private val dy: Float = dipF(9),
) : Drawable() {
    private var shadowRect = RectF(0f, 0f, 0f, 0f)

    private val shadowLayerPaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            alpha = 255
            style = Paint.Style.FILL
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    init {
        shadowLayerPaint.color = backgroundColor
        shadowLayerPaint.setShadowLayer(
            shadowWidth,
            dx,
            dy,
            colorAlpha(shadowColor, shadowAlpha)
        )
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(shadowRect, radius, radius, shadowLayerPaint)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        shadowRect = RectF(bounds)
    }

    override fun setAlpha(alpha: Int) {
        shadowLayerPaint.alpha = alpha
        invalidateSelf()
    }

    fun setBackgroundColor(color: Int) {
        shadowLayerPaint.color = color
        invalidateSelf()
    }

    fun setShadowColor(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float) {
        shadowLayerPaint.setShadowLayer(shadowWidth, dx, dy, colorAlpha(color, alpha))
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        shadowLayerPaint.colorFilter = colorFilter
        invalidateSelf()
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("PixelFormat.TRANSLUCENT", "android.graphics.PixelFormat")
    )
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    class Builder {
        private var backgroundColor: Int = Color.WHITE
        private var shadowColor: Int = Color.WHITE
        private var radius: Float = dipF(8)
        private var shadowWidth: Float = dipF(25)
        private var shadowAlpha: Float = 1f
        private var dx: Float = 0f
        private var dy: Float = 0f

        fun setY(value: Float): Builder {
            dy = value
            return this
        }

        fun setX(value: Float): Builder {
            dx = value
            return this
        }

        fun setAlpha(value: Float): Builder {
            shadowAlpha = value
            return this
        }

        fun setShadowWidth(value: Float): Builder {
            this.shadowWidth = value
            return this
        }

        fun setRadius(value: Float): Builder {
            this.radius = value
            return this
        }

        fun setBackgroundColor(color: Int): Builder {
            this.backgroundColor = color
            return this
        }

        fun setShadowColor(color: Int): Builder {
            this.shadowColor = color
            return this
        }

        fun setBackgroundWithShadowColor(color: Int): Builder {
            this.backgroundColor = color
            this.shadowColor = color
            return this
        }

        fun build(): ShadowDrawable {
            return ShadowDrawable(
                radius,
                backgroundColor,
                shadowColor,
                shadowWidth,
                shadowAlpha,
                dx,
                dy
            )
        }
    }

    companion object {
        fun getPrimary() = Builder()
            .setBackgroundColor(Color.WHITE)
            .setShadowColor(Color.RED)
            .setRadius(dipF(8))
            .setAlpha(0.2f)
            .setY(dipF(9))
            .setShadowWidth(dipF(8))
            .build()
    }
}