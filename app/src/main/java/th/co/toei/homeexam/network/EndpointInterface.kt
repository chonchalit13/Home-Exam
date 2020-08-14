package th.co.toei.homeexam.network

import retrofit2.Response
import retrofit2.http.GET
import th.co.toei.homeexam.model.PhotoListModel

interface EndpointInterface {

    @GET("albums/1/photos")
    suspend fun getPhotosList(): Response<MutableList<PhotoListModel>>

}