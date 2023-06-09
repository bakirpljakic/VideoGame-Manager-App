import com.google.gson.annotations.SerializedName

data class GameResponse (
    @SerializedName("igdb_id") val gameId: Int,
    @SerializedName("name")    val gameTitle: String
)