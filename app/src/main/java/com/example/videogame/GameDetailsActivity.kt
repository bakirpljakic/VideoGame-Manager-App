package com.example.videogame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.videogame.GameData.VideoGame.getDetails

class GameDetailsActivity : AppCompatActivity() {
    private lateinit var game: Game
    private lateinit var game_title: TextView
    private lateinit var coverImageView: ImageView
    private lateinit var platfrom: TextView
    private lateinit var rdate: TextView
    private lateinit var esrb: TextView
    private lateinit var developer: TextView
    private lateinit var publisher: TextView
    private lateinit var genre: TextView
    private lateinit var description: TextView
    private lateinit var home_button_2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details_activity)
        game_title = findViewById(R.id.game_title_text_view)
        platfrom = findViewById(R.id.platform_textview)
        rdate = findViewById(R.id.release_date_textview)
        esrb = findViewById(R.id.esrb_rating_textview)
        coverImageView = findViewById(R.id.cover_imageview)
        description = findViewById(R.id.description_textview)
        developer = findViewById(R.id.developer_textview)
        publisher = findViewById(R.id.publisher_textview)
        genre = findViewById(R.id.genre_textview)
        val extras = intent.extras
        if (extras != null) {
            game = getDetails(extras.getString("title", ""))!!
            populateDetails()

        }
        home_button_2 = findViewById(R.id.home_button)
        home_button_2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                putExtra("title", game.title)
            })
        }

    }



    private fun populateDetails() {
        game_title.text = game.title
        esrb.text = game.esrbRating
        platfrom.text = game.platform
        rdate.text = game.releaseDate
        developer.text = game.developer
        publisher.text = game.publisher
        description.text = game.description
        genre.text = game.genre
        val context: Context = coverImageView.context
        var id: Int = context.resources.getIdentifier(game.title, "drawable", context.packageName)
        if (id == 0) id =
            context.resources.getIdentifier("your_image", "drawable", context.packageName)
        coverImageView.setImageResource(id)

    }
}
