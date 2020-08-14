package th.co.toei.homeexam.usecase

import th.co.toei.homeexam.main.repository.MainActivityRepositoryImpl
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.model.Result

class GetPhotosListUseCase(private val mainActivityRepositoryImpl: MainActivityRepositoryImpl) :
    CoroutinesUseCase<Unit, MutableList<PhotoListModel>>() {
    override suspend fun execute(parameter: Unit): Result<MutableList<PhotoListModel>> {
        try {
            val response = mainActivityRepositoryImpl.getPhotosList()
            if (response.code() == 200 && response.body() != null) {
                response.body()?.let {
                    return Result.Success(it)
                } ?: return Result.Error("Error response null")
            } else {
                return Result.Error("Error code ${response.code()}")
            }
        } catch (e: Exception) {
            return Result.Error(e.message ?: "Error GetPhotosListUseCase")
        }
    }
}