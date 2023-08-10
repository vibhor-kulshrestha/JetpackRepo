import com.example.test.ApiResponse
import com.example.test.Category
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("v3/4509d8cc-2fe4-439c-9075-b9196c6ac455")
    suspend fun getCategories(): ApiResponse
}

object ApiClient {
    private const val BASE_URL = "https://run.mocky.io/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}