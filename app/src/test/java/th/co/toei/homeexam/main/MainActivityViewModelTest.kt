package th.co.toei.homeexam.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.model.Result
import th.co.toei.homeexam.usecase.GetPhotosListUseCase
import th.co.toei.homeexam.utils.LiveDataTestUtil

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPhotosListUseCase: GetPhotosListUseCase

    lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(getPhotosListUseCase)
        Dispatchers.setMain(newSingleThreadContext("UI Thread"))
    }

    @Test
    fun getPhotoListSuccess() {
        runBlocking {
            val response: MutableList<PhotoListModel> = mutableListOf(
                PhotoListModel(
                    1,
                    1,
                    "https://via.placeholder.com/150/92c952",
                    "Test",
                    "https://via.placeholder.com/600/92c952"
                )
            )

            Mockito.`when`(getPhotosListUseCase.execute(Unit)).thenReturn(Result.Success(response))
            mainActivityViewModel.getPhotoList()
            val result = LiveDataTestUtil.getValue(mainActivityViewModel.photosListLiveData)
            val error = LiveDataTestUtil.getValue(mainActivityViewModel.errorMessageLiveData)
            assertEquals("Test", result?.get(0)?.title)
            assertEquals(null, error)
        }
    }

    @Test
    fun getPhotoListError() {
        runBlocking {
            Mockito.`when`(getPhotosListUseCase.execute(Unit))
                .thenReturn(Result.Error("Error code 500"))
            mainActivityViewModel.getPhotoList()
            val result = LiveDataTestUtil.getValue(mainActivityViewModel.photosListLiveData)
            val error = LiveDataTestUtil.getValue(mainActivityViewModel.errorMessageLiveData)
            assertEquals(null, result)
            assertEquals("Error code 500", error)
        }
    }

    @Test
    fun photoListModelTest() {
        runBlocking {
            var photoListModel = PhotoListModel(
                1,
                1,
                "https://via.placeholder.com/150/92c952",
                "Test",
                "https://via.placeholder.com/600/92c952"
            )

            assertEquals(1, photoListModel.albumId)
            assertEquals(1, photoListModel.id)
            assertNotNull(photoListModel.title)
            assertNotNull(photoListModel.thumbnailUrl)
            assertNotNull(photoListModel.url)
        }
    }

    @After
    fun testDown() {
        Dispatchers.resetMain()
    }
}
