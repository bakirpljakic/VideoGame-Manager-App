package ba.etf.rma23.projekat

import android.annotation.SuppressLint
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class GameDeseralization : JsonDeserializer<Game> {
    @SuppressLint("SimpleDateFormat")

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Game {
        val jsonObject = json?.asJsonObject ?: JsonObject()
        val id = jsonObject.get("id")?.asInt ?: 0
        val title = jsonObject.get("name")?.asString ?: ""
        val platform = getPlatform(jsonObject)

        val epochTimeSeconds = jsonObject.getAsJsonPrimitive("first_release_date")?.asLong ?: 0
        val releaseDate = Date(epochTimeSeconds * 1000)
        val ld = LocalDate.ofEpochDay(epochTimeSeconds / 86400)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = ld.format(formatter)

        val rating = getRating(jsonObject)
        val coverImage = getCoverImage(jsonObject)
        val esrbRating = getEsrbRating(jsonObject)
        val developer = getCompany(jsonObject, "developer")
        val publisher = getCompany(jsonObject, "publisher")
        val genre = getGenre(jsonObject)
        val description = jsonObject.get("summary")?.asString ?: ""

        return Game(
            id, title, platform, formattedDate, rating, coverImage, esrbRating,
            developer, publisher, genre, description, emptyList()
        )
    }

    private fun getPlatform(jsonObject: JsonObject): String {
        val platformArray = jsonObject.getAsJsonArray("platforms")
        return if (platformArray != null && platformArray.size() > 0) {
            platformArray.joinToString(separator = "/") { it.asJsonObject.get("name").asString }
        } else {
            ""
        }
    }
    private fun getRating(jsonObject: JsonObject): Double {
        val ageRatingsArray = jsonObject.getAsJsonArray("age_ratings")
        return ageRatingsArray?.get(0)?.asJsonObject?.get("rating")?.asDouble ?: 0.0
    }

    private fun getCoverImage(jsonObject: JsonObject): String {
        val coverObject = jsonObject.getAsJsonObject("cover")
        return coverObject?.get("url")?.asString ?: ""
    }

    private fun getEsrbRating(jsonObject: JsonObject): String {
        val ageRatingsArray = jsonObject.getAsJsonArray("age_ratings")
        val esrbObject = ageRatingsArray
            ?.get(0)?.asJsonObject
            ?.getAsJsonObject("esrb")
        return esrbObject?.get("rating")?.asString ?: ""
    }

    private fun getCompany(jsonObject: JsonObject, type: String): String {
        val involvedCompaniesArray = jsonObject.getAsJsonArray("involved_companies")
        val companyObject = involvedCompaniesArray
            ?.firstOrNull { it.asJsonObject.get(type).asBoolean }
            ?.asJsonObject
            ?.getAsJsonObject("company")
        return companyObject?.get("name")?.asString ?: ""
    }

    private fun getGenre(jsonObject: JsonObject): String {
        val genreArray = jsonObject.getAsJsonArray("genres")
        return if (genreArray != null && genreArray.size() > 0) {
            genreArray.joinToString(separator = ",") { it.asJsonObject.get("name").asString }
        } else {
            "Unknown genre"
        }
    }
}
