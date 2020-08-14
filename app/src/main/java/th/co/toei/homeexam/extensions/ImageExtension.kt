package th.co.toei.homeexam.extensions

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun <T> ImageView.loadImage(url: T) {
    Glide.with(context).applyDefaultRequestOptions(
        RequestOptions.diskCacheStrategyOf(
            DiskCacheStrategy.AUTOMATIC
        )
    ).load(url).into(this)
}