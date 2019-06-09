package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.SubcategoriesResponse
import com.aurimteam.justhobby.response.SubcategoryResponse
import kotlinx.android.synthetic.main.dialog_subcategories.*

class SearchSubcategoriesFragment: BottomSheetDialogFragment(), ISearchSubcategoriesView {

    private val adapter = SearchSubcategoriesAdapter()
    private val presenter = SearchSubcategoriesPresenter(this, SearchSubcategoriesModel())

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_subcategories, container, false)
        return view
    }

    override fun showSubcategories(subcategories: List<SubcategoryResponse>) {
        adapter.onDataChange(subcategories)
    }

    override fun showServerMessage(message: String) {
        val toast = Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun onStart() {
        super.onStart()
        if (arguments != null)
            presenter.getSubcategories(context!!, arguments!!.get("categoryId")!!.toString().toInt())
        subcategoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        subcategoriesRecyclerView.adapter = adapter

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}