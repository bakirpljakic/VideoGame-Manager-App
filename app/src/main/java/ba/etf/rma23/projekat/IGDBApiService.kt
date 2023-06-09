package ba.etf.rma23.projekat

import ba.etf.unsa.rma23.projekat.BuildConfig
import retrofit2.Response
import retrofit2.http.*

private const val clientID: String = BuildConfig.CLIENT_ID
private const val authorization: String = BuildConfig.AUTHORIZATION

interface IGDBApiService {

    @Headers(

        "Client-ID: ugurgksxs15snq8zzkv2d1wtaheqy7",
        "Authorization: Bearer xvddnb6eugf27gr41wh72qbbnbiy7b",
        "Content-Type: application/json"
    )
    @GET("games")
    suspend fun getGamesByName(
        @Query("search") name: String,
        @Query("fields") fields: String = "id, name, platforms.name, first_release_date, rating, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name, genres, genres.name, summary"
    ): Response<List<Game>>


    @Headers(
        "Client-ID: $clientID",
        "Authorization: $authorization",
        "Content-Type: application/json"
    )
    @POST("games")
    suspend fun getGameByID(
        @Body body: okhttp3.RequestBody
    ): Response<List<Game>>

    @Headers(
        "Client-ID: $clientID",
        "Authorization: $authorization",
        "Content-Type: application/json"
    )
    @POST("games")
    suspend fun getGamesSafe(
        @Body body: okhttp3.RequestBody
    ): Response<List<Game>>


    @GET("games")
    suspend fun allGames(
        @Query("fields") fields: String = "id, name, platforms.name, first_release_date, rating, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name, genres, genres.name, summary"
    ): Response<List<Game>>

}
