package com.ds.safira.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.safira.R
import com.ds.safira.data.Review
import kotlinx.android.synthetic.main.item_review.view.*
import java.text.SimpleDateFormat

class ReviewAdapter(private val interaction: Interaction) :
    RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    private var data: List<Review> = mutableListOf()
    private val itemLayout = R.layout.item_review

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewHolder(inflater.inflate(itemLayout, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(data[position], interaction)
    }

    fun swapData(data: List<Review>) {
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    class ReviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Review, interaction: Interaction) = with(itemView) {
            Glide.with(itemView).load(item.imageUrl).into(roadImage)
            reviewDescription.text = item.description
            reviewReporter.text = item.reporter
            val date = SimpleDateFormat("dd MMM yyyy").format(item.createdAt)
            reviewInfo.text = "(${item.latitude - item.longitude}) pada $date"
            setOnClickListener { interaction.onItemClick(item) }
        }
    }

    interface Interaction {
        fun onItemClick(item: Review)
    }
}