package ba.etf.rma23.projekat.data.repositories
import ba.etf.rma23.projekat.GameDeseralization
import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.IGDBApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IGDBApiConfig {
    var baseURL: String = "https://api.igdb.com/v4/"
    val gsonbuild = GsonBuilder().registerTypeAdapter(Game::class.java, GameDeseralization()).create()
    val retrofit : IGDBApiService = Retrofit.Builder().
    baseUrl(baseURL).
    addConverterFactory(GsonConverterFactory.create(gsonbuild)).
    build().create(IGDBApiService::class.java)
}

