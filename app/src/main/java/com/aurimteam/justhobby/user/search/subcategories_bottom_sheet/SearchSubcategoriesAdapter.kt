package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.SubcategoryResponse
import kotlinx.android.synthetic.main.card_subcategories.view.*

class SearchSubcategoriesAdapter(private val presenter: SearchSubcategoriesPresenter) :
    RecyclerView.Adapter<SearchSubcategoriesHolder>() {

    private val subcategories: MutableList<SubcategoryResponse> = mutableListOf()
    private val isCheckedList: MutableMap<Int, Boolean> = mutableMapOf()

    override fun getItemCount(): Int = subcategories.size
    override fun onBindViewHolder(holder: SearchSubcategoriesHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            if (isCheckedList[subcategories[position].id] != null)
                holder.toggleCheckBox(isCheckedList[subcategories[position].id]!!)
    }

    override fun onBindViewHolder(holder: SearchSubcategoriesHolder, position: Int) {
        holder.bind(
            subcategories[position].attributes.title,
            isCheckedList[subcategories[position].id]
        )
        holder.itemView.subcategoryTitle.setOnCheckedChangeListener { _, isChecked ->
            isCheckedList[subcategories[position].id] = isChecked
            if (!isCheckedList.containsValue(false)) {
                presenter.checked(1)
            } else if (!isCheckedList.containsValue(true)) {
                presenter.checked(0)
            } else {
                presenter.checked(2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchSubcategoriesHolder = SearchSubcategoriesHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_subcategories, parent, false)
    )

    fun onDataChange(subcategories: List<SubcategoryResponse>, categoryId: Int, checks: Bundle) {
        this.subcategories.clear()
        this.subcategories.addAll(subcategories)
        val category = checks.getIntegerArrayList("category$categoryId")
        for (item in subcategories)
            isCheckedList[item.id] = category?.contains(item.id) ?: false

        if (!isCheckedList.containsValue(false)) {
            presenter.checked(1)
        } else if (!isCheckedList.containsValue(true)) {
            presenter.checked(0)
        } else {
            presenter.checked(2)
        }

        notifyDataSetChanged()
    }

    fun changeCheckBoxes(state: Boolean) {
        for (item in subcategories) {
            isCheckedList[item.id] = state
            notifyItemChanged(subcategories.indexOf(item))
        }
    }

    fun getIsCheckedList(): ArrayList<Int> {
        val returnArr = arrayListOf<Int>()
        for (item in isCheckedList)
            if(item.value)
                returnArr.add(item.key)
        return returnArr
    }
}