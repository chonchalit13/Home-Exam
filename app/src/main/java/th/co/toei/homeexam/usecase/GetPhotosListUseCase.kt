package th.co.toei.homeexam.usecase

import th.co.toei.homeexam.repository.MainRepositoryImpl
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.model.Result

class GetPhotosListUseCase(private val mainActivityRepositoryImpl: MainRepositoryImpl) :
    CoroutinesUseCase<Unit, MutableList<PhotoListModel>>() {
    override suspend fun execute(parameter: Unit): Result<MutableList<PhotoListModel>> {
        return try {
            val response = mainActivityRepositoryImpl.getPhotosList()
            if (response.code() == 200 && response.body() != null) {
                Result.Success(response.body() ?: mutableListOf())
            } else {
                Result.Error("Error code ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error GetPhotosListUseCase")
        }
    }
}