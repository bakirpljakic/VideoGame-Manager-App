package ba.etf.rma23.projekat

import ba.etf.rma23.projekat.data.repositories.GamesRepository
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import java.lang.reflect.Type

class AccountDeseralization : JsonDeserializer<Game> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Game {
        val jsonObject = json?.asJsonObject ?: JsonObject()
        val id = jsonObject.get("igdb_id")?.asInt ?: 0

        var game = Game(id,"","","",0.0,"","","",
            "", "", "", emptyList())

        runBlocking {
            val gameResponse = CoroutineScope(Dispatchers.IO).async {
                GamesRepository.getGameByID(id)
            }
            val response = gameResponse.await()

            if (response.isNotEmpty()) {
                game = response[0]
            }
        }

        return game
    }
}
