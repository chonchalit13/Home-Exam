package th.co.toei.homeexam.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class ApiServiceTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var mockWebServer: MockWebServer
    lateinit var retrofit: Retrofit
    lateinit var mService: EndpointInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        retrofit = Retrofit.Builder()
            .client(ApiService().okHttp)
            .baseUrl(mockWebServer.url("https://jsonplaceholder.typicode.com/").toString())
            .addConverterFactory(GsonConverterFactory.create(ApiService().gson))
            .build()

        mService = ApiService().getEndpointInterface(EndpointInterface::class.java)

        Dispatchers.setMain(newSingleThreadContext("UI Thread"))
    }

    @Test
    fun callService() {
        runBlocking {
            val call = mService.getPhotosList()
            assertEquals(200, call.code())
            assertNotNull(call.body())
        }
    }

    @After
    fun testDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }
}