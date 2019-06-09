package com.aurimteam.justhobby.user.search.search

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CategoryResponse
import com.aurimteam.justhobby.user.course_info.course_reviews.CourseReviewsFragment
import com.aurimteam.justhobby.user.search.subcategories_bottom_sheet.SearchSubcategoriesFragment
import kotlinx.android.synthetic.main.card_category.view.*

class SearchAdapter : RecyclerView.Adapter<SearchHolder>() {

    private val categories: MutableList<CategoryResponse> = mutableListOf()

    override fun getItemCount(): Int = categories.size
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(
            categories[position].attributes.slug,
            categories[position].attributes.title
        )
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.itemView.subcategoryBottomSheet.setOnClickListener {
            openSubcategories(
                manager,
                categories[position].id
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchHolder =
        SearchHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)
        )

    fun onDataChange(categories: List<CategoryResponse>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    private fun openSubcategories(fragmentManager: FragmentManager, categoryId: Int) {
        val bundle = Bundle()
        bundle.putInt("categoryId", categoryId)
        val searchSubcategoriesFragment = SearchSubcategoriesFragment()
        searchSubcategoriesFragment.arguments = bundle
        searchSubcategoriesFragment.show(fragmentManager, "Subcategories")
    }
}