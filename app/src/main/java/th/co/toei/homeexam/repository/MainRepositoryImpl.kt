package th.co.toei.homeexam.repository

import retrofit2.Response
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.network.EndpointInterface

class MainRepositoryImpl(private val mService: EndpointInterface) : MainRepository {

    override suspend fun getPhotosList(): Response<MutableList<PhotoListModel>> = mService.getPhotosList()

}