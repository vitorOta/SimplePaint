package com.vitorota.simplepaint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val path = Path()

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeJoin = Paint.Join.MITER
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 7f
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(event.x, event.y)
            MotionEvent.ACTION_MOVE -> path.lineTo(event.x, event.y)
        }

        postInvalidate()
        return true
    }

    fun clear() {
        path.reset()
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        canvas.drawPath(path, paint)
    }
}