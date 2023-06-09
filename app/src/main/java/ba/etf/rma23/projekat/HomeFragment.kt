package ba.etf.rma23.projekat

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.GameData.VideoGame.getAll
import ba.etf.rma23.projekat.GameData.VideoGame.getDetails
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import ba.etf.unsa.rma23.projekat.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchBar
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {
    private lateinit var videogameView: RecyclerView
    private lateinit var videogameAdapter: GameListAdapter
    private lateinit var searchBar: EditText
    private lateinit var searchButton: Button
    private lateinit var sortGamesButton: Button
    private var games = getAll()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val videogameView = view.findViewById<RecyclerView>(R.id.game_list)
        searchBar = view.findViewById(R.id.search_query_edittext)
        searchButton = view.findViewById(R.id.search_button)
        sortGamesButton = view.findViewById(R.id.sort_button)
        runBlocking {
            games = GamesRepository.getGamesByName("")
        }

        videogameView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = GameListAdapter(games) { game -> showGameDetails(game) }
        }
        val bundle: Bundle? = arguments
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val navigation: BottomNavigationView =
                requireActivity().findViewById(R.id.bottomNavigation)
            val detailsNavItem: BottomNavigationItemView =
                navigation.findViewById(R.id.gameDetailsItem)

            if (bundle?.getString("videoGame") == null) {
                navigation.menu.findItem(R.id.gameDetailsItem).isEnabled = false
                navigation.menu.findItem(R.id.homeItem).isEnabled = false
            }
            val homeNavItem: BottomNavigationItemView =
                navigation.findViewById(R.id.homeItem)
            homeNavItem.setOnClickListener {
            }
            videogameAdapter = GameListAdapter(listOf()) { game -> showGameDetails(game) }
            videogameView.adapter = videogameAdapter
            videogameAdapter.updateGames(games)
            searchButton.setOnClickListener {
                runBlocking() {
                    games = GamesRepository.getGamesByName(searchBar.text.toString())
                }
                videogameAdapter.updateGames(games)
            }
            sortGamesButton.setOnClickListener{
                runBlocking(){
                    games = GamesRepository.sortGames()
                }
                videogameAdapter.updateGames(games)
            }


            navigation.menu.findItem(R.id.homeItem).isChecked = false
            detailsNavItem.setOnClickListener {
                var game = getDetails(bundle!!.getString("videoGame", ""))
                if (game != null) {
                    showGameDetails(game)
                }
            }
        }
        return view
    }

    private fun showGameDetails(game: Game) {
        val bundle = Bundle().apply {
            putString("videoGame", game.title)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val gameDetailsFragment = GameDetailsFragment()
            gameDetailsFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.details_fragment, gameDetailsFragment)
                commit()
            }
        } else {
            view?.findNavController()?.navigate(R.id.homeToGame, bundle)
        }
    }
}

