package com.example.gameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videogame.R
import com.example.videogame.UserImpression
import com.example.videogame.UserRating
import com.example.videogame.UserReview

class UserImpressionAdapter(private var userImpressions: List<UserImpression>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val RATING = 0
        private const val COMMENT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == RATING) {
            val view = inflater.inflate(R.layout.rating_bar, parent, false)
            return UserRatingViewHolder(view)
        } else if (viewType == COMMENT) {
            val view = inflater.inflate(R.layout.comment, parent, false)
            return UserReviewViewHolder(view)
        } else {
            throw IllegalArgumentException("Undefined view type")
        }
    }

    fun updateImpressions(impressions: List<UserImpression>) {
        this.userImpressions = impressions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userImpressions.size

    override fun getItemViewType(position: Int): Int {
        val userImpression = userImpressions[position]
        if (userImpression is UserRating) {
            return RATING
        } else if (userImpression is UserReview) {
            return COMMENT
        } else {
            throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userImpression = userImpressions[position]
        if (holder is UserRatingViewHolder) {
            val rating = userImpression as UserRating
            holder.username.text = rating.userName
            holder.rating.rating = rating.rating.toFloat()
        } else if (holder is UserReviewViewHolder) {
            val review = userImpression as UserReview
            holder.username.text = review.userName
            holder.review.text = review.review
        }
    }

    inner class UserRatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rating: RatingBar = itemView.findViewById(R.id.rating_bar)
        val username: TextView = itemView.findViewById(R.id.username_textview)
    }

    inner class UserReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val review: TextView = itemView.findViewById(R.id.review_textview)
        val username: TextView = itemView.findViewById(R.id.username_textview)
    }

}
