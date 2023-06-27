package ba.etf.rma23.projekat


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.etf.rma23.projekat.data.repositories.GameReviewsRepository
import ba.etf.rma23.projekat.data.repositories.GameReviewsRepository.getOfflineReviews
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import ba.etf.unsa.rma23.projekat.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private val gamesRepository = GamesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

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
        }
}
