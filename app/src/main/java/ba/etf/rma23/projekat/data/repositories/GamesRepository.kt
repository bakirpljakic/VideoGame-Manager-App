package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.data.repositories.IGDBApiConfig.retrofit
import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.RequestBody.Companion.toRequestBody


object GamesRepository {

    var displayedGames: List<Game> = emptyList()
    suspend fun getGamesByName(name: String): List<Game> = withContext(Dispatchers.IO) {
        try {
            val response = retrofit.getGamesByName(name)

            if (response.isSuccessful) {
                displayedGames = response.body()!!
                return@withContext response.body() ?: emptyList()
            } else {
                return@withContext emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList()
        }
    }

    suspend fun getGameByID(id: Int): List<Game> {
        return try {
            withContext(Dispatchers.IO) {
                val stringID = id.toString()
                val string =
                    "fields id, name, platforms.name, release_dates.human, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name,genres, genres.name, summary;" +
                            "where id = $stringID;"
                val response = retrofit.getGameByID(string.toRequestBody())
                response.body() ?: emptyList()
            }
        } catch (e: Exception) {
            println(e.message)
            emptyList()
        }
    }

    suspend fun getOneGame(game: String): Game {
        return getGamesByName(game).get(0)
    }

    suspend fun getGamesSafe(name: String): List<Game> {
        return try {
            val ageUser = AccountGamesRepository.uAge
            if (ageUser in 4..99) {
                val ageToString = ageUser.toString()
                val query =
                    "fields id, name, platforms.name, first_release_date, rating, age_ratings.rating, age_ratings.category, cover.url, involved_companies, " +
                            "involved_companies.developer, involved_companies.publisher, involved_companies.company.name, " +
                            "genres, genres.name, summary; where name = \"$name\"; where age_ratings.rating <= $ageToString;"
                val response = withContext(Dispatchers.IO) {
                    retrofit.getGamesSafe(query.toRequestBody())
                }
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            println(e.message)
            emptyList()
        }
    }


    suspend fun sortGames(): List<Game> = withContext(Dispatchers.Default) {
        val savedGames = AccountGamesRepository.getSavedGames()
        return@withContext try {
            val sortedFavorites = savedGames.sortedBy { it.title }
            val sortedNonF = savedGames.filter { it !in sortedFavorites }
                .sortedBy { it.title }
            sortedFavorites + sortedNonF
        } catch (e: Exception) {
            print(e.message)
            emptyList()
        }
    }


}
