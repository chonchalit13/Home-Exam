package th.co.toei.homeexam.main.repository

import retrofit2.Response
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.network.EndpointInterface

class MainActivityRepositoryImpl(private val mService: EndpointInterface) : MainActivityRepository {

    override suspend fun getPhotosList(): Response<MutableList<PhotoListModel>> = mService.getPhotosList()

}