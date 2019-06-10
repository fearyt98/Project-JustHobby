package com.aurimteam.justhobby.user.search.search

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.card_category.view.*

class SearchHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        slug: String,
        title: String,
        isCheckedN: Boolean?
    ) {
        var isChecked = false
        if (isCheckedN != null)
            isChecked = isCheckedN
        var color = R.color.whiteTop
        when (slug) {
            "languages" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_languages)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_languages)
                else
                    color = R.color.languages
            }
            "sports" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_sports)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_sports)
                else
                    color = R.color.sports
            }
            "musics" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_musics)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_musics)
                else
                    color = R.color.musics
            }
            "it" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_it)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_it)
                else
                    color = R.color.it
            }
            "dances" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_dances)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_dances)
                else
                    color = R.color.dances
            }
            "art_design" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_art_design)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_art_design)
                else
                    color = R.color.art_design
            }
            "craft" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_craft)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_craft)
                else
                    color = R.color.craft

            }
            "designing" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_designing)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_designing)
                else
                    color = R.color.designing

            }
            "literature" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_literature)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_literature)
                else
                    color = R.color.literature
            }
            "fashion_style" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_fashion_style)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_fashion_style)
                else
                    color = R.color.fashion_style

            }
            "entertainment_arts" -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_entertainment_arts)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_entertainment_arts)
                else
                    color = R.color.entertainment_arts

            }
            else -> {
                itemView.cardCategoryImage.setImageResource(R.drawable.category_ic_languages)
                if (isChecked)
                    itemView.cardCategory.setBackgroundResource(R.drawable.card_timeline_bg_languages)
                else
                    color = R.color.languages

            }
        }
        if(!isChecked)
            itemView.cardCategory.setBackgroundResource(R.drawable.card_category_bg)
        itemView.cardCategoryImage.setColorFilter(
            ContextCompat.getColor(
                itemView.context,
                color
            )
        )
        itemView.cardCategoryTitle.setTextColor(ContextCompat.getColor(itemView.context, color))
        itemView.cardCategoryTitle.text = title

    }
}