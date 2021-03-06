package th.co.toei.homeexam.view.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.co.toei.homeexam.R
import th.co.toei.homeexam.alert.AlertMessageDialogFragment
import th.co.toei.homeexam.base.BaseActivity
import th.co.toei.homeexam.view.detail.DetailActivity
import th.co.toei.homeexam.view.main.adapter.MainActivityAdapter
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.viewmodel.MainActivityViewModel

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

        mainActivityAdapter.setOnItemClickListener(object :
            MainActivityAdapter.OnItemClickListener {
            override fun onItemClick(item: PhotoListModel) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(PHOTO_DETAIL, item)
                }
                startActivity(intent)
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
        const val PHOTO_DETAIL = "PHOTO_DETAIL"
    }
}
