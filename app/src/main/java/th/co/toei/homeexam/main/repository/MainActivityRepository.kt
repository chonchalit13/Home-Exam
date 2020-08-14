package th.co.toei.homeexam.main.repository

import retrofit2.Response
import th.co.toei.homeexam.model.PhotoListModel

interface MainActivityRepository {
    suspend fun getPhotosList(): Response<MutableList<PhotoListModel>>
}