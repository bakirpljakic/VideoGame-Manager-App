package ba.etf.rma23.projekat.data.repositories
import ba.etf.rma23.projekat.data.repositories.IGDBApiConfig.retrofit
import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.RequestBody.Companion.toRequestBody


object GamesRepository {

    suspend fun getGamesByName(name: String): List<Game> = withContext(Dispatchers.IO) {
        try {
            val response = retrofit.getGamesByName(name)

            if (response.isSuccessful) {
                return@withContext response.body() ?: emptyList()
            } else {
                return@withContext emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext emptyList()
        }
    }

    suspend fun getGameByID(id: Int): List<Game> = withContext(Dispatchers.IO) {
        try {
        val stringID = id.toString()
        val string =
            "fields id, name, platforms.name, release_dates.human, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name,genres, genres.name, summary;" +
                    "where id = $stringID;"
        val response = retrofit.getGameByID(string.toRequestBody())
        print(response.body())
        return@withContext response.body()!!
        } catch (e: Exception) {
            print(e.message)
            return@withContext emptyList()
        }
    }
    suspend fun getGamesSafe(name: String): List<Game> {
        return try {
            val age = AccountGamesRepository.uAge
            if (age in 4..99) {
                val ageString = age.toString()
                val query =
                    "fields id, name, platforms.name, first_release_date, rating, age_ratings.rating, age_ratings.category, cover.url, involved_companies, " +
                            "involved_companies.developer, involved_companies.publisher, involved_companies.company.name, " +
                            "genres, genres.name, summary; where name = \"$name\"; where age_ratings.rating <= $ageString;"
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

    suspend fun getOneGame(game: String):Game{
        return getGamesByName(game).get(0)
    }
    //var allGamesList: List<Game> = emptyList()

   /* suspend fun allGames(): List<Game> = withContext(Dispatchers.IO) {
        return@withContext try {
            val responseBody = retrofit.allGames().body()
            allGamesList = responseBody ?: emptyList()
            responseBody ?: emptyList()
        } catch (e: Exception) {
            print(e.message)
            emptyList()
        }
    }*/


    suspend fun sortGames(): List<Game> = withContext(Dispatchers.Default) {
        val savedGames = AccountGamesRepository.getSavedGames()
        return@withContext try {
            val sortedFavoriteGames = savedGames.sortedBy { it.title }
            val sortedNonFavoriteGames = savedGames.filter { it !in sortedFavoriteGames }
                .sortedBy { it.title }
            sortedFavoriteGames + sortedNonFavoriteGames
        } catch (e: Exception) {
            print(e.message)
            emptyList()
        }
    }


}
