package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.GameInfo
import ba.etf.rma23.projekat.data.repositories.AccountApiConfig.retrofit
import ba.etf.rma23.projekat.RequestBody
import kotlinx.coroutines.*


object AccountGamesRepository {

    var uHash: String = "7a8110d0-d3f9-41b7-bd01-67910f80afd3"
    var uAge = 0

    fun getHash(): String {
        return this.uHash
    }

    fun setHash(acHash: String): Boolean {
        uHash = acHash
        return true
    }

    fun setAge(age: Int): Boolean {
        this.uAge = age
        if (age in 3..100) {
            return true
        }
        return false
    }

    /* suspend fun getSavedGames(): List<Game> = withContext(Dispatchers.IO) {
         try {
             val response = retrofit.getSavedGames()
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
 */

    suspend fun getSavedGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            var response = AccountApiConfig.retrofit.getSavedGames(uHash!!)
            val lista: MutableList<Game> = mutableListOf()
            for (i in 0 until response.size) {
                lista.add(GamesRepository.getGamesByName(response[i].name)[0])
            }
            return@withContext lista
        }
    }

    suspend fun removeGame(id: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = retrofit.removeGame(getHash(), id)
            return@withContext response.body() == true
        } catch (e: Exception) {
            return@withContext true
        }
    }

    suspend fun saveGame(game: Game): Game {
        return try {
            var updatedGame = game
            val requestBody = RequestBody(game.id, game.title)
            val response = withContext(Dispatchers.IO) {
                retrofit.saveGame(GameInfo(requestBody))
            }

            val gameResponse = withContext(Dispatchers.IO) {
                GamesRepository.getGameByID(response.gameId)
            }

            val gameResult = if (gameResponse.isNotEmpty()) gameResponse[0] else game

            updatedGame = gameResult

            updatedGame
        } catch (e: Exception) {
            print(e.message)
            Game(0, "", "", "", 0.0, "", "", "", "", "", "", emptyList())
        }
    }

    suspend fun getGamesContainingString(query: String): List<Game> {
        return try {
            val favoriteList = withContext(Dispatchers.IO) {
                getSavedGames()
            }
            favoriteList.filter { it.title == query }
        } catch (e: Exception) {
            println(e.message)
            emptyList()
        }
    }


}