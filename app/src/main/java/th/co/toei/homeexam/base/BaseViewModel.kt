package th.co.toei.homeexam.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
}