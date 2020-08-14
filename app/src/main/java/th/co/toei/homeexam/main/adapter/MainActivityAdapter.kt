package th.co.toei.homeexam.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_list_item_photo.view.*
import th.co.toei.homeexam.R
import th.co.toei.homeexam.extensions.loadImage
import th.co.toei.homeexam.model.PhotoListModel


class MainActivityAdapter : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    private var mItems: MutableList<PhotoListModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mItems[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: PhotoListModel) {
            itemView.tv_title.text = item.title

            val url = GlideUrl(item.thumbnailUrl, Headers.DEFAULT)
            itemView.imv_thumbnail.loadImage(url)
        }
    }

    fun setListData(response: MutableList<PhotoListModel>) {
        mItems.clear()
        mItems.addAll(response)
        notifyDataSetChanged()
    }
}