import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.FragmentActivity
import com.safwen.boxffficetest.R

open class CircularProgressBarDialog(val c: FragmentActivity?)// TODO Auto-generated constructor stub
    : Dialog(c!!) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        setContentView(R.layout.circular_progress_bar)


    }



}