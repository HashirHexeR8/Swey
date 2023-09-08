import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.Window
import androidx.core.content.ContextCompat
import com.business.swey.R

class BlurredOverlayDialog(context: Context) : Dialog(context) {
    private val blurredOverlay: Bitmap

    init {
        // Disable the default background
        window?.setBackgroundDrawable(null)
        // Apply the blurred overlay background
        blurredOverlay = createBlurredOverlay()
    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        // Draw the blurred overlay background
//        canvas?.drawBitmap(blurredOverlay, 0f, 0f, null)
//    }

    private fun createBlurredOverlay(): Bitmap {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_background_blur)
        val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
