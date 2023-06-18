package ba.etf.rma23.projekat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ReviewDao {
    @Query("SELECT * FROM gamereview")
    suspend fun getAll(): List<GameReview>
    @Insert
    suspend fun insertAll(vararg review: GameReview)

}