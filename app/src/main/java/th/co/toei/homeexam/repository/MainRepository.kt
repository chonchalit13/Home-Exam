package th.co.toei.homeexam.repository

import retrofit2.Response
import th.co.toei.homeexam.model.PhotoListModel

interface MainRepository {
    suspend fun getPhotosList(): Response<MutableList<PhotoListModel>>
}