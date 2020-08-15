package th.co.toei.homeexam.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoListModel(
    @SerializedName("albumId")
    val albumId: Int = 0,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("url")
    val url: String = ""
) : Parcelable