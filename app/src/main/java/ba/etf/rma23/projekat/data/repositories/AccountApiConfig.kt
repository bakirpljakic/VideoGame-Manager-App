package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.AccountApiService
import ba.etf.rma23.projekat.AccountDeseralization
import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.IGDBApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AccountApiConfig {
    var baseURL: String = "https://rma23ws.onrender.com/"
    val gsonbuild = GsonBuilder().registerTypeAdapter(Game::class.java, AccountDeseralization()).create()
    val retrofit : AccountApiService = Retrofit.Builder().
    baseUrl(baseURL).
    addConverterFactory(GsonConverterFactory.create(gsonbuild)).
    build().create(AccountApiService::class.java)
}