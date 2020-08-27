package th.co.toei.homeexam.view.main.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.layout_list_item_photo.view.*
import th.co.toei.homeexam.R
import th.co.toei.homeexam.view.main.MainActivity
import th.co.toei.homeexam.model.PhotoListModel

class MainActivityAdapter : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    private var mItems: MutableList<PhotoListModel> = mutableListOf()
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mItems[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: PhotoListModel) {
            itemView.tv_title.text = item.title

            loadImage(item, itemView)

            itemView.setOnClickListener {
                mListener?.onItemClick(item)
            }
        }

        private fun loadImage(item: PhotoListModel, itemView: View) {
            val url = GlideUrl(
                item.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", MainActivity.TAG)
                    .build()
            )

            itemView.progress_bar.visibility = View.VISIBLE

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .load(url)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_bar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_bar.visibility = View.GONE
                        return false
                    }
                })
                .skipMemoryCache(true)
                .error(R.drawable.ic_broken)
                .into(itemView.imv_thumbnail)
        }
    }

    fun setListData(response: MutableList<PhotoListModel>) {
        mItems.clear()
        mItems.addAll(response)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: PhotoListModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }
}
