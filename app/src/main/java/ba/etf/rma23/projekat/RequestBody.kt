package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class RequestBody (
    @SerializedName("igdb_id") val gameId: Int,
    @SerializedName("name")    val gameTitle: String
)