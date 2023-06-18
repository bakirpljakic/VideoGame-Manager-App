package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReviewApiConfig {
    var baseURL: String = "https://rma23ws.onrender.com/"
    val gsonbuild = GsonBuilder().registerTypeAdapter(Game::class.java, AccountDeseralization()).create()
    val retrofit : ReviewApiService = Retrofit.Builder().
    baseUrl(baseURL).
    addConverterFactory(GsonConverterFactory.create(gsonbuild)).
    build().create(ReviewApiService::class.java)
}