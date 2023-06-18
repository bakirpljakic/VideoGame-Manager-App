package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import ba.etf.rma23.projekat.AppDatabase
import ba.etf.rma23.projekat.GameReview
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


object GameReviewsRepository {

    suspend fun getOfflineReviews(context: Context): List<GameReview> {
        return withContext(Dispatchers.IO) {
            val database = AppDatabase.getInstance(context)
            val gameReviewDao = database.reviewDao()
            val allReviews = gameReviewDao.getAll()
            val offlineReviews = allReviews.filter { !it.online }
            return@withContext offlineReviews
        }
    }

    suspend fun getReviewsForGame(igdb_id: Int): List<GameReview> {
        return withContext(Dispatchers.IO) {
            try {
                val response = ReviewApiConfig.retrofit.getReviewsForGame(igdb_id.toString())
                    val responseBody = response.body()
                    return@withContext responseBody ?: emptyList()
            } catch (e: Exception) {
                throw IOException("Neuspjesno")
            }
        }
    }

    suspend fun sendReview(gr: GameReview): Boolean {
        val reviews = getReviewsForGame(gr.igdb_id)
        if(reviews.isEmpty()) return false
        if (reviews.any { it.id == gr.id }) {
            return false
        }

        val requestBody = JSONObject().apply {
            put("rating", gr.rating)
            put("review", gr.review)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        return withContext(Dispatchers.IO) {
            try {
                ReviewApiConfig.retrofit.sendReview(
                    getHash(),
                    gr.igdb_id.toString(),
                    requestBody
                )
                true
            } catch (e: Exception) {
                false
            }
        }
    }


    suspend fun sendOfflineReviews(): Int {
        return withContext(Dispatchers.IO) {
            val database = AppDatabase.getInstance(getApplicationContext())
            val gameReviewDao = database.reviewDao()
            val offlineReviews = gameReviewDao.getAll().filter { !it.online }

            var count = 0
            for (review in offlineReviews) {
                if (sendReview(review)) {
                    count++
                    gameReviewDao.insertAll(review)
                }
            }

            return@withContext count
        }
    }



}