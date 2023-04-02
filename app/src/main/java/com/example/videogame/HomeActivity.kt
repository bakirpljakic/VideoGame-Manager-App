package com.example.videogame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videogame.GameData.VideoGame.getAll
import com.example.videogame.GameData.VideoGame.getDetails

class HomeActivity : AppCompatActivity() {
    private lateinit var videogameView: RecyclerView
    private lateinit var videogameAdapter: GameListAdapter
    private lateinit var details_button: Button
    private var games = getAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        details_button = findViewById(R.id.details_button)
        val extras = intent.extras
        if (extras != null) {
            details_button.isEnabled = true
        } else {
            details_button.isEnabled = false
        }
        details_button.setOnClickListener {
            if (extras != null) {
                details_button.isEnabled = true
                val game = getDetails(extras.getString("videoGame", ""))
                showGameDetails(game!!)
            }
        }

        videogameView = findViewById(R.id.game_list)
        videogameView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        videogameAdapter = GameListAdapter(arrayListOf()) { game ->
            showGameDetails(game)
        }
        videogameView.adapter = videogameAdapter
        videogameAdapter.updateGames(games)
        details_button = findViewById(R.id.details_button)
    }

    private fun showGameDetails(game: Game) {
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("videoGame", game.title)
        }
        startActivity(intent)
    }

}