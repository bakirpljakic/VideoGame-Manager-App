package ba.etf.rma23.projekat

import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository.getHash
import retrofit2.Response
import retrofit2.http.*

interface AccountApiService {
    companion object {
        const val CONTENT_TYPE_JSON = "Content-Type: application/json"
    }
    @Headers(CONTENT_TYPE_JSON)
    @GET("account/{aid}/games")
    suspend fun getSavedGames(
        @Path("aid") aid: String = getHash()
    ): List<GameResponse>

    @Headers(CONTENT_TYPE_JSON)
    @DELETE("account/{aid}/game/{gid}")
    suspend fun removeGame(
        @Path("aid") aid: String = getHash(),
        @Path("gid") gid:Int
    ): Response<Boolean>

    @Headers(CONTENT_TYPE_JSON)
    @POST("account/{aid}/game")
    suspend fun saveGame(
        @Body requestBody: GameInfo,
        @Path("aid") aid: String = getHash()
    ): RequestBody

}

