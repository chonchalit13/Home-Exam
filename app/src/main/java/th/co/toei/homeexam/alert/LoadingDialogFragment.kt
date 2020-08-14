package th.co.toei.homeexam.alert

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import th.co.toei.homeexam.R

class LoadingDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        dialog?.window?.setBackgroundDrawableResource((R.drawable.bg_loading_dialog))
        return inflater.inflate(R.layout.layout_loading_dialog, container, false)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.let {
            it.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }
    }

    class Builder {
        fun build(): LoadingDialogFragment {
            return newInstance()
        }
    }

    companion object {
        fun newInstance(): LoadingDialogFragment = LoadingDialogFragment()
    }
}