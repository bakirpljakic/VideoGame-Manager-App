package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("igdb_id") val id: Int,
    @SerializedName("name") val name: String
)