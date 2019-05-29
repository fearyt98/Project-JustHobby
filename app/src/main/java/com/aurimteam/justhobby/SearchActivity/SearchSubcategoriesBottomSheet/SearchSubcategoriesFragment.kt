package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.SubcategoriesResponse
import kotlinx.android.synthetic.main.bottomsheets_subcategories.*

class SearchSubcategoriesFragment : BottomSheetDialogFragment(), ISearchSubcategoriesView {

    private val adapter = SearchSubcategoriesAdapter()
    private val presenter = SearchSubcategoriesPresenter(this, SearchSubcategoriesModel())

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottomsheets_subcategories, container, false)
        return view
    }

    override fun showSubcategories(subcategories: List<SubcategoriesResponse>) {
        adapter.onDataChange(subcategories)
    }

    override fun onStart() {
        super.onStart()
        presenter.getSubcategories()
        subcategoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        subcategoriesRecyclerView.adapter = adapter

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}