package com.example.videogame

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videogame.GameData.VideoGame.getAll
import com.example.videogame.GameData.VideoGame.getDetails
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private lateinit var videogameView: RecyclerView
    private lateinit var videogameAdapter: GameListAdapter
    private var games = getAll()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val videogameView = view.findViewById<RecyclerView>(R.id.game_list)
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

