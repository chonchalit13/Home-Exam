package th.co.toei.homeexam.base

import androidx.appcompat.app.AppCompatActivity
import th.co.toei.homeexam.alert.LoadingDialogFragment

open class BaseActivity : AppCompatActivity() {

    private var loadingDialogFragment: LoadingDialogFragment? = null

    fun showLoading() {
        if (loadingDialogFragment == null) {
            loadingDialogFragment = LoadingDialogFragment.Builder().build()
        }

        loadingDialogFragment?.let {
            if (!it.isVisible) {
                it.show(supportFragmentManager, TAG)
            }
        }

    }

    fun dismissLoading() {
        loadingDialogFragment?.let {
            it.dismiss()
        }
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}