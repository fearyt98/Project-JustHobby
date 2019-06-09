package com.aurimteam.justhobby.user.search.search

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.card_category.view.*

class SearchHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        slug: String,
        title: String
    ) {
        when (slug) {
            "languages" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_languages)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.languages
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.languages))
            }
            "sports" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_sports)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.sports
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.sports))
            }
            "musics" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_musics)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.musics
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.musics))
            }
            "it" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_it)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.it
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.it))
            }
            "dances" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_dances)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.dances
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.dances))
            }
            "art_design" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_art_design)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.art_design
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.art_design))
            }
            "craft" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_craft)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.craft
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.craft))
            }
            "designing" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_designing)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.designing
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.designing))
            }
            "literature" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_literature)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.literature
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.literature))
            }
            "fashion_style" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_fashion_style)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.fashion_style
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.fashion_style))
            }
            "entertainment_arts" ->  {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_entertainment_arts)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.entertainment_arts
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.entertainment_arts))
            }
            else -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_languages)
                itemView.cardCategoryImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.languages
                    )
                )
                itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.languages))
            }
        }
        itemView.cardCategoryTitle.text = title
    }
}