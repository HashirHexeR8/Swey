package com.business.swey.core.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.R
import com.business.swey.features.home.chat.adapters.viewHolders.ReceiveTextViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendTextViewHolder
import kotlin.math.absoluteValue

class SwipeHelper(mContext: Context, private val thresholdX: Float, private val onSwipedCallback: (Int) -> Unit) :
    ItemTouchHelper.Callback() {
    private val mClearPaint: Paint = Paint()
    private val replyDrawable: Drawable
    private val intrinsicWidth: Int
    private val intrinsicHeight: Int
    private var thresholdReached = false

    init {
        mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        replyDrawable = AppCompatResources.getDrawable(mContext, R.drawable.ic_reply)!!
        intrinsicWidth = mContext.resources.getDimension(com.intuit.sdp.R.dimen._16sdp).toInt()
        intrinsicHeight = mContext.resources.getDimension(com.intuit.sdp.R.dimen._16sdp).toInt()
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return when (viewHolder) {
            is SendTextViewHolder -> makeMovementFlags(0, ItemTouchHelper.LEFT)
            is ReceiveTextViewHolder -> makeMovementFlags(0, ItemTouchHelper.RIGHT)
            else -> 0
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        viewHolder1: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val isCancelled = dX == 0f && !isCurrentlyActive
        if (dX <= -thresholdX) {
            // stop the item on left swipe threshold
            drawReplyIcon(c, itemView, -thresholdX, thresholdX)
            thresholdReached = true
            super.onChildDraw(c, recyclerView, viewHolder, -thresholdX, dY, actionState, isCurrentlyActive)
            return
        }

        if (dX >= thresholdX) {
            // stop the item on right swipe threshold
            thresholdReached = true
            drawReplyIcon(c, itemView, thresholdX, thresholdX)
            super.onChildDraw(c, recyclerView, viewHolder, thresholdX, dY, actionState, isCurrentlyActive)
            return
        }

        if (isCancelled) {
            if (thresholdReached){
                onSwipedCallback.invoke(viewHolder.adapterPosition)
                thresholdReached = false
            }
            clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        drawReplyIcon(c, itemView, dX, thresholdX)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawReplyIcon(c: Canvas, itemView: View, dX: Float, thresholdX: Float) {
        val itemHeight = itemView.height
        val isSwipeToLeft = dX < 0

        val replyIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val replyIconBottom = replyIconTop + intrinsicHeight

        // Adjust the left and right positions based on the swipe direction
        val replyIconLeft: Int
        val replyIconRight: Int
        if (isSwipeToLeft) {
            replyIconLeft = itemView.right - intrinsicWidth + (dX / 2.5).toInt()
            replyIconRight = itemView.right + (dX / 2.5).toInt()
        } else {
            replyIconLeft = itemView.left + (dX / 2.5).toInt()
            replyIconRight = itemView.left + intrinsicWidth + (dX / 2.5).toInt()
        }

        // Draw the reply icon
        replyDrawable.alpha = ((dX.absoluteValue/thresholdX) * 255).toInt()
        replyDrawable.setBounds(replyIconLeft, replyIconTop, replyIconRight, replyIconBottom)
        replyDrawable.draw(c)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, mClearPaint)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = 1.0f
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float = 0.3f
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}

