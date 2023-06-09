package ba.etf.rma23.projekat


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import ba.etf.unsa.rma23.projekat.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val gamesRepository = GamesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

     /*   CoroutineScope(Job() + Dispatchers.Main).launch {
            AccountGamesRepository.setHash("ca0ee672-440b-45b2-8a12-75b80f4fbdd3")
            AccountGamesRepository.saveGame(Game(24273,"Age of Empires: The Age of Kings","","",10.0,"","","","","","",listOf<UserImpression>()))
            AccountGamesRepository.saveGame(Game(47076,"Age of Empires: Gold Edition","","",10.0,"","","","","","",listOf<UserImpression>()))
            var res = AccountGamesRepository.getSavedGames()
            print(res.size)
            print(res)
        }*/
        //print(res[0].releaseDate)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navView.setupWithNavController(navController)
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.details_fragment, GameDetailsFragment())
                replace(R.id.home_fragment, HomeFragment())
                commit()
            }
        }
      /*  CoroutineScope(Job() + Dispatchers.Main).launch {
            val response = AccountGamesRepository.getSavedGames()
            val a = response.size
            print(a)
            val ime = response.get(0).title
            print(ime)
            val pl = response.get(0).platform.get(0)
            print(pl)*/
            //MatcherAssert.assertThat(res.size, CoreMatchers.equalTo(2))
            //MatcherAssert.assertThat(res.get(0).releaseDate, CoreMatchers.containsString("2006"))
      /*  MatcherAssert.assertThat(res.size, CoreMatchers.equalTo(2))
        MatcherAssert.assertThat(res.get(0).releaseDate, CoreMatchers.containsString("2006"))
        MatcherAssert.assertThat(res.get(1).releaseDate, CoreMatchers.containsString("1999"))*/
/*
          //val config: Configuration = baseContext.resources.configuration
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val response = GamesRepository.getGamesByName("Age of empires")
            val a = response.size
            print(a)
            val ime = response.get(2).releaseDate
            print(ime)
            val pl = response.get(0).platform.get(0)
            print(pl)
            val id = response.get(0).id
            print(id)
            val rating = response.get(0).rating
            print(rating)
            val esrb = response.get(0).esrbRating
            print(esrb)
            val developer = response.get(0).developer
            print(developer)
            val pub = response.get(0).publisher
            print(pub)
            val release = response.get(0).releaseDate
            print(release)
        }

*/
          /*  CoroutineScope(Job() + Dispatchers.Main).launch {
             //   val game = GamesRepository.getGameByID(50975)

                val game2 = Game(5, "Adi", "", "", 0.0, "", "", "", "", "", "", emptyList())
                AccountGamesRepository.saveGame(game2)
            }*/
    }}
                /*if (game != null) {
                    val id = game.get(0).id
                    print(id)
                    val name = game.get(0).title
                    print(name)
                    val platforms = game.get(0).platform
                    print(platforms)
                    // ... continue printing other properties as needed
                } else {
                    // Handle the case when the game is not found or there is an error
                    println("Game not found or there was an error.")
                }
            }
        }
    }

/*
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val response = GamesRepository.getGameByID(1942)
            /*  val a = response.id
            print(a)*/
            val ime = response!!.title
            print(ime)
        }}}
            val pl = response.get(0).platform.get(0)
            print(pl)
            val id = response.get(0).id
            print(id)
            val rating = response.get(0).rating
            print(rating)
            val esrb = response.get(0).esrbRating
            print(esrb)
            val developer = response.get(0).developer
            print(developer)
            val pub = response.get(0).publisher
            print(pub)
            val release = response.get(0).releaseDate
            print(release)
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = GamesRepository.getGameByID(1942)

                if (response != null) {
                    val a = 1 // Since we have only one game, set 'a' to 1
                    println(a)

                    val ime = response.title
                    println(ime)

                    val pl = response.platform.get(0)
                    println(pl)

                    val id = response.id
                    println(id)

                    val rating = response.age_ratings?.get(0)?.rating
                    println(rating)

                    val esrb = response.age_ratings?.get(0)?.category
                    println(esrb)

                    val developer = response.involved_companies?.filter { it.developer == true }?.get(0)?.company?.name
                    println(developer)

                    val pub = response.involved_companies?.filter { it.publisher == true }?.get(0)?.company?.name
                    println(pub)

                    val release = response.first_release_date
                    println(release)
                } else {
                    println("Game not found")
                }
            }} catch (e: Exception) {
                e.printStackTrace()
            }


    }


*/