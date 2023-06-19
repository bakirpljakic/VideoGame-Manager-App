package ba.etf.rma23.projekat

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.GameData.VideoGame.getAll
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import ba.etf.rma23.projekat.data.repositories.GameReviewsRepository
import ba.etf.unsa.rma23.projekat.R
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class GameDetailsFragment : Fragment() {
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
    private lateinit var impressionAdapter: UserImpressionAdapter
    private lateinit var impressionView: RecyclerView
    private var impressions: List<UserImpression>? = null
    private lateinit var saveButton: Button
    private lateinit var removeButton: Button
    private lateinit var addRating: RatingBar
    private lateinit var addReview: EditText
    private lateinit var uploadButton: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_details, container, false)

        gameTitle = view.findViewById(R.id.game_title_text_view)
        coverImageView = view.findViewById(R.id.cover_imageview)
        gamePlatfrom = view.findViewById(R.id.platform_textview)
        gameReleaseDate = view.findViewById(R.id.release_date_textview)
        gameEsrb = view.findViewById(R.id.esrb_rating_textview)
        gameDeveloper = view.findViewById(R.id.developer_textview)
        gamePublisher = view.findViewById(R.id.publisher_textview)
        gameGenre = view.findViewById(R.id.genre_textview)
        gameDescription = view.findViewById(R.id.description_textview)
        saveButton = view.findViewById(R.id.save_game_button)
        removeButton = view.findViewById(R.id.remove_game_button)
        addReview = view.findViewById(R.id.review_text)
        addRating = view.findViewById(R.id.ratingBar)
        uploadButton = view.findViewById(R.id.upload_rating)


        val bundle: Bundle? = arguments
        if (bundle == null) {
            game = getAll()[0]
            populateDetails()
        } else {
            game = bundle.getString("videoGame")?.let { GameData.getDetails(it) }!!
            populateDetails()
           // impressions = game!!.userImpressions!!.sortedByDescending { it.timestamp }
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val navigation: BottomNavigationView =
                requireActivity().findViewById(R.id.bottomNavigation)
            val homeNavItem: BottomNavigationItemView =
                navigation.findViewById(R.id.homeItem)
            val detailsNavItem: BottomNavigationItemView =
                navigation.findViewById(R.id.gameDetailsItem)
            navigation.menu.findItem(R.id.homeItem).isEnabled = true
            homeNavItem.setOnClickListener {
                requireView().findNavController().navigate(R.id.gameToHome, bundle)
            }
            navigation.menu.findItem(R.id.gameDetailsItem).isEnabled = true
            detailsNavItem.setOnClickListener {
            }
            navigation.menu.findItem(R.id.gameDetailsItem).isChecked = true
        }
        saveButton.setOnClickListener{
            runBlocking { AccountGamesRepository.saveGame(game) }
        }
        removeButton.setOnClickListener {
            runBlocking { AccountGamesRepository.removeGame(game.id) }
        }

        val gameReviews: List<GameReview> = runBlocking {
            GameReviewsRepository.getReviewsForGame(game.id)
        }


           impressions = gameReviews.flatMap { item ->
            val ratings = if (item.rating != null)
                listOf(UserRating("Anonymous", 0L, item.rating!!))
            else emptyList()

            val reviews = if (item.review != null && item.review != "null")
                listOf(UserReview("Anonymous", 0L, item.review!!))
            else emptyList()

            ratings + reviews
        }

        impressionView = view.findViewById(R.id.impression_list);
        impressionView.layoutManager = LinearLayoutManager(requireContext())
        impressionAdapter = game.userImpressions?.let { UserImpressionAdapter(it) }!!
        impressionView.adapter = impressionAdapter
        impressions?.let { impressionAdapter.updateImpressions(it) }
        var changedRating: Boolean = false

        addRating.setOnRatingBarChangeListener { _, _, _ ->
            changedRating = true
        }

        uploadButton.setOnClickListener {
            val rating: Int? = if (changedRating) addRating.rating.toInt() else null
            val review: String? = if (!addReview.text.toString().isEmpty()) addReview.text.toString() else null

            if (changedRating || !addReview.text.isNullOrEmpty()) {
                runBlocking {
                    val gameReview = GameReview(0, rating, review, game.id, false)
                    AppDatabase.getInstance(requireContext()).reviewDao().insertAll(gameReview)

                    GameReviewsRepository.getOfflineReviews(requireContext()).size
                    addRating.rating = 0F
                    changedRating = false
                    addReview.text?.clear()
                }
            }
        }
        return view

    }

    override fun onStop() {
        super.onStop()
        lifecycleScope.launch {
            GameReviewsRepository.sendOfflineReviews(requireContext())
        }
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
        val context: Context = coverImageView.context
       var id: Int = context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        Glide.with(coverImageView.context).
        load("https://"+game.coverImage).error(id).fallback(id).into(coverImageView)
    }

}