package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class AccountResponse (
    @SerializedName("igdb_id") val gameId: Int,
    @SerializedName("name")    val gameTitle: String
)