package ba.etf.rma23.projekat
import com.google.gson.annotations.SerializedName


data class Game(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("platforms.name") val platform: String,
    @SerializedName("first_release_date") val releaseDate: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("cover.url") val coverImage: String?,
    @SerializedName("age_ratings.rating") val esrbRating: String?,
    @SerializedName("involved_companies.developer") val developer: String?,
    @SerializedName("involved_companies.publisher") val publisher: String?,
    @SerializedName("genres.name") val genre: String?,
    @SerializedName("summary") val description: String?,
    val userImpressions: List<UserImpression> = emptyList()
)
