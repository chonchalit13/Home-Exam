package th.co.toei.homeexam.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import th.co.toei.homeexam.main.repository.MainActivityRepositoryImpl
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.model.Result
import th.co.toei.homeexam.network.EndpointInterface

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class GetPhotosListUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mService: EndpointInterface

    lateinit var mainActivityRepositoryImpl: MainActivityRepositoryImpl

    lateinit var getPhotosListUseCase: GetPhotosListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainActivityRepositoryImpl = MainActivityRepositoryImpl(mService)
        getPhotosListUseCase = GetPhotosListUseCase(mainActivityRepositoryImpl)
        Dispatchers.setMain(newSingleThreadContext("UI Thread"))
    }

    @Test
    fun getPhotoListUseCaseSuccess() {
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

            Mockito.`when`(mainActivityRepositoryImpl.getPhotosList())
                .thenReturn(Response.success(response))

            when (val result = getPhotosListUseCase.execute(Unit)) {
                is Result.Success -> {
                    assertNotNull(result.data)
                }
                is Result.Error -> {
                    assertNotNull(result.exception)
                }
            }
        }
    }

    @Test
    fun getPhotoListUseCaseError() {
        runBlocking {
            Mockito.`when`(mainActivityRepositoryImpl.getPhotosList())
                .thenReturn(Response.error(500, EMPTY_RESPONSE))

            when (val result = getPhotosListUseCase.execute(Unit)) {
                is Result.Success -> {
                    assertNotNull(result.data)
                }
                is Result.Error -> {
                    assertNotNull(result.exception)
                }
            }
        }
    }

    @Test
    fun getPhotoListUseCaseResponseNull() {
        runBlocking {
            Mockito.`when`(mainActivityRepositoryImpl.getPhotosList())
                .thenReturn(Response.error(500, EMPTY_RESPONSE))

            when (val result = getPhotosListUseCase.execute(Unit)) {
                is Result.Success -> {
                    assertNotNull(result.data)
                }
                is Result.Error -> {
                    assertNotNull(result.exception)
                }
            }
        }
    }

    @Test
    fun getPhotoListUseCaseCatch() {
        runBlocking {
            when (val result = getPhotosListUseCase.execute(Unit)) {
                is Result.Success -> {
                    assertNotNull(result.data)
                }
                is Result.Error -> {
                    assertNotNull(result.exception)
                }
            }
        }
    }

    @After
    fun testDown() {
        Dispatchers.resetMain()
    }
}
