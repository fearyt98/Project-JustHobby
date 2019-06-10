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
    private val isCheckedList: MutableMap<Int, Boolean> = mutableMapOf()

    override fun getItemCount(): Int = categories.size
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(
            categories[position].attributes.slug,
            categories[position].attributes.title,
            isCheckedList[categories[position].id]
        )
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.itemView.cardCategory.setOnClickListener {
            openSubcategories(
                manager,
                categories[position].id,
                categories[position].attributes.title
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
        for (item in categories)
            isCheckedList[item.id] = false
        notifyDataSetChanged()
    }

    fun changeChecked(categoriesBundle: Bundle) {
        for (item in categories)
            if (categoriesBundle.containsKey("category${item.id}") && categoriesBundle.getIntegerArrayList("category${item.id}")?.size != 0) {
                if (!isCheckedList[item.id]!!)
                    changeChecked(item.id, true, categories.indexOf(item))
            } else
                if (isCheckedList[item.id]!!)
                    changeChecked(item.id, false, categories.indexOf(item))
    }

    private fun changeChecked(categoryId: Int, isChecked: Boolean, position: Int) {
        isCheckedList[categoryId] = isChecked
        notifyItemChanged(position)
    }

    private fun openSubcategories(fragmentManager: FragmentManager, categoryId: Int, categoryName: String) {
        val searchFragment =
            fragmentManager.findFragmentById(R.id.mainNavContainerFragment) as SearchFragment

        val bundle = searchFragment.getCategories()
        bundle.putInt("categoryId", categoryId)
        bundle.putString("categoryName", categoryName)
        val searchSubcategoriesFragment = SearchSubcategoriesFragment()
        searchSubcategoriesFragment.arguments = bundle
        searchSubcategoriesFragment.show(fragmentManager, "Subcategories")
    }
}