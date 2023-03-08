package com.jack.shadowview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

private const val DEFAULT_BACKGROUND_COLOR = Color.WHITE
private const val DEFAULT_SHADOW_COLOR = Color.RED
private const val DEFAULT_SHADOW_WIDTH = 35f
private const val DEFAULT_SHADOW_RADIUS = 25f
private const val DEFAULT_SHADOW_PIVOT_X = 0f
private const val DEFAULT_SHADOW_PIVOT_Y = 0f
private const val DEFAULT_SHADOW_ALPHA = 0.5f

class ShadowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var backgroundRect = RectF(0f, 0f, 0f, 0f)
    private var shadowWidth = dip(12).toFloat()
    private var radius = dip(8).toFloat()

    private val shadowLayerPaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            alpha = 255
            style = FILL
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowView)
            val backgroundColor = typedArray.getColor(R.styleable.ShadowView_backgroundColor, DEFAULT_BACKGROUND_COLOR)
            shadowWidth = typedArray.getDimension(R.styleable.ShadowView_shadowWidth, DEFAULT_SHADOW_WIDTH)
            radius = typedArray.getDimension(R.styleable.ShadowView_radius, DEFAULT_SHADOW_RADIUS)
            val dx = typedArray.getFloat(R.styleable.ShadowView_android_pivotX, DEFAULT_SHADOW_PIVOT_X)
            val dy = typedArray.getFloat(R.styleable.ShadowView_android_pivotY, DEFAULT_SHADOW_PIVOT_Y)
            val shadowAlpha = typedArray.getFloat(R.styleable.ShadowView_shadowAlpha, DEFAULT_SHADOW_ALPHA)
            val shadowColor = typedArray.getColor(R.styleable.ShadowView_shadowColor, DEFAULT_SHADOW_COLOR)

            setShadowColor(backgroundColor, shadowColor, shadowAlpha, dx, dy, radius, shadowWidth)
            typedArray.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateShadowWidth(w, h)
    }

    fun calculateShadowWidth(width: Int, height: Int) {
        backgroundRect = RectF(
            paddingLeft.toFloat() + shadowWidth,
            paddingTop + shadowWidth,
            width - paddingRight - shadowWidth,
            height - paddingBottom - shadowWidth
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        (this.parent as ViewGroup).clipChildren = false
        (this.parent as ViewGroup).clipToPadding = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(backgroundRect, radius, radius, shadowLayerPaint)
    }

    fun setShadowColor(
        @ColorInt backgroundColor: Int,
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float,
        pivotX: Float,
        pivotY: Float,
        radius: Float,
        shadowWidth: Float,
    ) {
        this.radius = radius
        shadowLayerPaint.setShadowLayer(radius, pivotX, pivotY, colorAlpha(color, alpha))
        shadowLayerPaint.color = backgroundColor
        this.shadowWidth = shadowWidth
        calculateShadowWidth(width, height)
        invalidate()
    }
}
