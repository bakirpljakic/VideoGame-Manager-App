package ba.etf.rma23.projekat

import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository.getHash
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ReviewApiService {

    companion object {
        const val CONTENT_TYPE_JSON = "Content-Type: application/json"
    }

    @Headers(CONTENT_TYPE_JSON)
    @GET("game/{gid}/gamereviews")
    suspend fun getReviewsForGame(
        @Path("gid") gid: String
    ): Response<List<GameReview>>

    @Headers(CONTENT_TYPE_JSON)
    @POST("account/{aid}/game/{gid}/gamereview")
    suspend fun sendReview(
        @Path("aid") aid: String,
        @Path("gid") gid: String,
        @Body review: RequestBody
    )

}