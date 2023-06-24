package ba.etf.rma23.projekat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

@Entity
data class GameReview (
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "rating") @SerializedName("rating") val rating : Int?,
    @ColumnInfo(name = "review") @SerializedName("review") val review: String?,
    @ColumnInfo(name = "igdb_id") var igdb_id:Int,
    @ColumnInfo(name = "online") var online: Boolean,
    @SerializedName("student") val userName: String,
    @SerializedName("timestamp") val timestamp: String
) {
    constructor(
        rating: Int?,
        review: String?,
        igdb_id: Int,
        online: Boolean,
        username: String,
        timestamp: String
    ) : this(getRandom(3,19000), rating, review, igdb_id, online, username, timestamp)
}
private fun getRandom(start: Int, end: Int):Int{
    require(start <= end){"Invalid range"}
    return Random.nextInt(start, end + 1)
}
