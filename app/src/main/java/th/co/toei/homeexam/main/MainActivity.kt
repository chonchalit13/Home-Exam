package th.co.toei.homeexam.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.co.toei.homeexam.R
import th.co.toei.homeexam.alert.AlertMessageDialogFragment
import th.co.toei.homeexam.base.BaseActivity
import th.co.toei.homeexam.main.adapter.MainActivityAdapter

class MainActivity : BaseActivity() {

    private val vm: MainActivityViewModel by viewModel()

    private lateinit var mainActivityAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeData()
        initAdapter()

        vm.getPhotoList()
    }

    private fun observeData() {
        vm.photosListLiveData.observe(this, Observer {
            mainActivityAdapter.setListData(it)
        })

        vm.loadingLiveData.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })

        vm.errorMessageLiveData.observe(this, Observer {
            AlertMessageDialogFragment.Builder()
                .setMessage(it)
                .build()
                .show(supportFragmentManager, TAG)
        })
    }

    private fun initAdapter() {
        mainActivityAdapter = MainActivityAdapter()

        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = mainActivityAdapter
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
