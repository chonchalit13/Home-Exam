package th.co.toei.homeexam.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.co.toei.homeexam.base.BaseViewModel
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.model.Result
import th.co.toei.homeexam.usecase.GetPhotosListUseCase

class MainActivityViewModel(private val getPhotosListUseCase: GetPhotosListUseCase) :
    BaseViewModel() {

    val photosListLiveData: MutableLiveData<MutableList<PhotoListModel>> = MutableLiveData()

    fun getPhotoList() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            withContext(Dispatchers.IO) {
                when (val result = getPhotosListUseCase.execute(Unit)) {
                    is Result.Success -> {
                        loadingLiveData.postValue(false)
                        photosListLiveData.postValue(result.data)
                    }
                    is Result.Error -> {
                        loadingLiveData.postValue(false)
                        errorMessageLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}