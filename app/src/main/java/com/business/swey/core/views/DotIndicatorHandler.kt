import android.content.Context
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.business.swey.R

class DotIndicatorHandler(private val context: Context, private val container: LinearLayout) {

    private val dots = mutableListOf<View>()
    fun setupDots(itemCount: Int) {
        container.removeAllViews()
        dots.clear()

        for (i in 0 until itemCount) {
            val dotView = View(context).apply {

                val targetWidth = if (i == 0) 14.dp else 6.dp
                val targetHeight = if (i == 0) 6.dp else 6.dp

                layoutParams = LinearLayout.LayoutParams(targetWidth, targetHeight).apply {
                    marginEnd = 4.dp
                }
                setBackgroundResource(R.drawable.dot_inactive)
            }
            container.addView(dotView)
            dots.add(dotView)
        }
    }

    fun updateDots(position: Int) {
        dots.forEachIndexed { index, view ->
            val targetWidth = if (index == position) 14.dp else 6.dp
            val targetHeight = if (index == position) 6.dp else 6.dp

            val transition = AutoTransition().apply {
                duration = 100
                interpolator = AccelerateDecelerateInterpolator()
            }

            TransitionManager.beginDelayedTransition(container, transition)

            val params = view.layoutParams as LinearLayout.LayoutParams
            params.width = targetWidth
            params.height = targetHeight
            view.layoutParams = params

            // Change background resource
            view.setBackgroundResource(if (index == position) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }

    private val Int.dp: Int
        get() = (this * context.resources.displayMetrics.density).toInt()
}
