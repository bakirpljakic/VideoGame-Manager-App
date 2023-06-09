package ba.etf.rma23.projekat

import android.annotation.SuppressLint
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.text.DecimalFormat
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
        val ets = jsonObject.getAsJsonPrimitive("first_release_date")?.asLong ?: 0
        val local = LocalDate.ofEpochDay(ets / 86400)
        val f = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val releaseDate = local.format(f)

        val rating = jsonObject.get("rating")?.asDouble ?: 0.0
        val ratingFormatted = DecimalFormat("#.##").format(rating)
        val ratingRounded = ratingFormatted.toDoubleOrNull() ?: 0.0

        val coverImage = getCoverImage(jsonObject)
        val esrbRating = getEsrbRating(jsonObject)
        val developer = getCompany(jsonObject, "developer")
        val publisher = getCompany(jsonObject, "publisher")
        val genre = getGenre(jsonObject)
        val description = jsonObject.get("summary")?.asString ?: ""

        return Game(
            id, title, platform, releaseDate, ratingRounded, coverImage, esrbRating,
            developer, publisher, genre, description, emptyList()
        )
    }

    private fun getPlatform(jsonObject: JsonObject): String {
        val platformArray = jsonObject.getAsJsonArray("platforms")
        return if (platformArray != null && platformArray.size() > 0) {
            platformArray.joinToString(separator = "/") { it.asJsonObject.get("name").asString }
        } else {
            "Not Available"
        }
    }
  /*  private fun getRating(jsonObject: JsonObject): Double {
        val ageRatingsArray = jsonObject.getAsJsonArray("age_ratings")
        return ageRatingsArray?.get(0)?.asJsonObject?.get("rating")?.asDouble ?: 0.0
    }
*/
    private fun getCoverImage(jsonObject: JsonObject): String {
        val coverObject = jsonObject.getAsJsonObject("cover")
        return coverObject?.get("url")?.asString ?: ""
    }

    private fun getEsrbRating(jsonObject: JsonObject): String {
        val ratingArray = jsonObject.getAsJsonArray("age_ratings")
        if (ratingArray != null && ratingArray.size() > 0) {
            for (i in 0 until ratingArray.size()) {
                val objectArray = ratingArray[i].asJsonObject.get("category")?.asInt ?: 0
                if (objectArray == 2 || objectArray == 1) {
                    val esrbJson = ratingArray[i].asJsonObject.get("rating")?.asInt ?: 0
                    return convertESRB(esrbJson)
                }
            }
        }
        return "No ESRB rating"
    }
    private fun convertESRB(value: Int): String {
        return ESRB.values().find { it.value == value }?.name ?: ""
    }


    private fun getCompany(jsonObject: JsonObject, type: String): String {
        val involvedCompaniesArray = jsonObject.getAsJsonArray("involved_companies")
        val companyObject = involvedCompaniesArray
            ?.firstOrNull { it.asJsonObject.get(type).asBoolean }
            ?.asJsonObject
            ?.getAsJsonObject("company")
        return companyObject?.get("name")?.asString ?: "Not Available"
    }

    private fun getGenre(jsonObject: JsonObject): String {
        val genreArray = jsonObject.getAsJsonArray("genres")
        return if (genreArray != null && genreArray.size() > 0) {
            genreArray.joinToString(separator = ",") { it.asJsonObject.get("name").asString }
        } else {
            "Not Available"
        }
    }
}
