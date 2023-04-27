package com.example.videogame

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GameListAdapter(
    private var games: List<Game>,
    private val onItemClicked: (game:Game) -> Unit
) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return GameViewHolder(view)
    }
    override fun getItemCount(): Int = games.size
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.gameTitle.text = games[position].title;
        holder.gameRating.text = games[position].rating.toString()
        holder.gameReleaseDate.text = games[position].releaseDate
        holder.gamePlatform.text = games[position].platform
        holder.itemView.setOnClickListener{ onItemClicked(games[position]) }
    }
    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }
    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameRating: TextView = itemView.findViewById(R.id.game_rating_textview)
        val gameTitle: TextView = itemView.findViewById(R.id.item_title_textview)
        val gameReleaseDate: TextView = itemView.findViewById(R.id.game_release_date_textview)
        val gamePlatform: TextView = itemView.findViewById(R.id.game_platform_textview)
    }
}