package th.co.toei.homeexam.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
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
import th.co.toei.homeexam.model.PhotoListModel
import th.co.toei.homeexam.network.EndpointInterface

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainRepositoryImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mService: EndpointInterface

    lateinit var mainActivityRepositoryImpl: MainRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainActivityRepositoryImpl = MainRepositoryImpl(mService)
        Dispatchers.setMain(newSingleThreadContext("UI Thread"))
    }

    @Test
    fun getPhotosListSuccess() {
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

            val result = mainActivityRepositoryImpl.getPhotosList()
            assertEquals("Test", result.body()?.get(0)?.title)
            assertEquals(200, result.code())
        }
    }

    @Test
    fun getPhotosListError() {
        runBlocking {
            Mockito.`when`(mainActivityRepositoryImpl.getPhotosList())
                .thenReturn(Response.error(500, EMPTY_RESPONSE))

            val result = mainActivityRepositoryImpl.getPhotosList()
            assertNotNull(result.message())
            assertEquals(500, result.code())
        }
    }

    @After
    fun testDown() {
        Dispatchers.resetMain()
    }
}