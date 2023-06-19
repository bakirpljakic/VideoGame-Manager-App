package ba.etf.rma23.projekat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameReview(
    @PrimaryKey(true) @SerializedName("id") var id: Int,
    @ColumnInfo(name  = "rating") @SerializedName("rating") var rating: Int?,
    @ColumnInfo(name  = "review") @SerializedName("review") var review: String?,
    @ColumnInfo(name  = "gid") @SerializedName("GameId") var igdb_id: Int,
    @ColumnInfo(name  = "online")var online: Boolean
)

