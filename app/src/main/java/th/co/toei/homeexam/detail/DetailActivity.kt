package th.co.toei.homeexam.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_detail.*
import th.co.toei.homeexam.R
import th.co.toei.homeexam.main.MainActivity
import th.co.toei.homeexam.model.PhotoListModel

class DetailActivity : AppCompatActivity() {

    private var photoListModel: PhotoListModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.extras?.let {
            photoListModel = it.get(MainActivity.PHOTO_DETAIL) as PhotoListModel
        }

        photoListModel?.let {
            supportActionBar?.apply {
                title = it.title
                setDisplayHomeAsUpEnabled(true)
            }
            loadImage(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadImage(item: PhotoListModel) {
        val url = GlideUrl(
            item.url, LazyHeaders.Builder()
                .addHeader("User-Agent", TAG)
                .build()
        )

        progress_bar.visibility = View.VISIBLE

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .load(url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_bar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_bar.visibility = View.GONE
                    return false
                }
            })
            .skipMemoryCache(true)
            .error(R.drawable.ic_broken)
            .into(imv_detail)
    }

    companion object {
        const val TAG = "DetailActivity"
    }
}