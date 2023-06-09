package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.GameInfo
import ba.etf.rma23.projekat.data.repositories.AccountApiConfig.retrofit
import ba.etf.rma23.projekat.AccountResponse
import ba.etf.rma23.projekat.ESRB
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


    suspend fun getSavedGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            var response = AccountApiConfig.retrofit.getSavedGames(uHash!!)
            val savedGames: MutableList<Game> = mutableListOf()
            for (i in 0 until response.size) {
                savedGames.add(GamesRepository.getGamesByName(response[i].gameTitle)[0])
            }
            return@withContext savedGames
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
            val accountResponse = AccountResponse(game.id, game.title)
            val response = withContext(Dispatchers.IO) {
                retrofit.saveGame(GameInfo(accountResponse))
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

    suspend fun removeNonSafe(): Boolean {
        val age = uAge
        val favoriteList = getSavedGames()
        val validAgeList = mutableListOf<Game>()

        for (game in favoriteList) {
            if (game.esrbRating != "No ESRB rating") {
                val esrbValue = game.esrbRating?.let { getEsrbRatingByString(it) }
                val ratingString = esrbValue?.name ?: continue
                val value = realAge(ratingString)
                if (value != -1 && value > age) {
                    removeGame(game.id)
                } else {
                    validAgeList.add(game)
                }
            } else {
                validAgeList.add(game)
            }
        }
        return true
    }

    fun getEsrbRatingByString(esrb: String): ESRB? {
        return ESRB.values().find { it.name == esrb }
    }

    fun realAge(value: String): Int {
        return when (value) {
            "Three" -> 3
            "Seven" -> 7
            "Twelve" -> 12
            "Sixteen" -> 16
            "Eighteen", "RP", "AO" -> 18
            "EC" -> 3
            "E" -> 0
            "E10" -> 10
            "T" -> 13
            "M" -> 17
            else -> -1
        }
    }



}