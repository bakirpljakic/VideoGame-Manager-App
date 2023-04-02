package com.example.videogame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.UserImpressionAdapter
import com.example.videogame.GameData.VideoGame.getDetails

class GameDetailsActivity : AppCompatActivity() {
    private lateinit var game: Game
    private lateinit var gameTitle: TextView
    private lateinit var coverImageView: ImageView
    private lateinit var gamePlatfrom: TextView
    private lateinit var gameReleaseDate: TextView
    private lateinit var gameEsrb: TextView
    private lateinit var gameDeveloper: TextView
    private lateinit var gamePublisher: TextView
    private lateinit var gameGenre: TextView
    private lateinit var gameDescription: TextView
    private lateinit var homebutton2: Button
    private lateinit var impressionAdapter: UserImpressionAdapter
    private lateinit var impressionView: RecyclerView
    private lateinit var impressions: List<UserImpression>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details_activity)
        gameTitle = findViewById(R.id.game_title_text_view)
        coverImageView = findViewById(R.id.cover_imageview)
        gamePlatfrom = findViewById(R.id.platform_textview)
        gameReleaseDate = findViewById(R.id.release_date_textview)
        gameEsrb = findViewById(R.id.esrb_rating_textview)
        gameDeveloper = findViewById(R.id.developer_textview)
        gamePublisher = findViewById(R.id.publisher_textview)
        gameGenre = findViewById(R.id.genre_textview)
        gameDescription = findViewById(R.id.description_textview)
        val extras = intent.extras
        if (extras != null) {
            game = getDetails(extras.getString("videoGame", ""))!!
            populateDetails()
            impressions = game!!.userImpressions!!.sortedByDescending { it.timestamp }

        }
        homebutton2 = findViewById(R.id.home_button)
        homebutton2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                putExtra("videoGame", game.title)
            })
        }
        impressionView = findViewById(R.id.impression_list);
        impressionView.setLayoutManager(LinearLayoutManager(this));
        impressionAdapter = game.userImpressions?.let { UserImpressionAdapter(it) }!!;
        impressionView.setAdapter(impressionAdapter);
        impressionAdapter.updateImpressions(impressions)
    }

    private fun populateDetails() {
        gameTitle.text = game.title
        gamePlatfrom.text = game.platform
        gameEsrb.text = game.esrbRating
        gameReleaseDate.text = game.releaseDate
        gameDeveloper.text = game.developer
        gamePublisher.text = game.publisher
        gameDescription.text = game.description
        gameGenre.text = game.genre
        val imageName = game.title.replace(" ", "_").lowercase()
        val context: Context = coverImageView.context
        var id: Int = context.resources
            .getIdentifier(imageName, "drawable", context.packageName)
        if (id === 0) id = context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        coverImageView.setImageResource(id)
    }
}
