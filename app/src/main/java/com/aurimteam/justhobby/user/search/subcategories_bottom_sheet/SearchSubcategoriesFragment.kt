package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.SubcategoryResponse
import com.aurimteam.justhobby.user.search.search.SearchFragment
import com.buildware.widget.indeterm.IndeterminateCheckBox
import kotlinx.android.synthetic.main.card_subcategories.*
import kotlinx.android.synthetic.main.dialog_subcategories.*

class SearchSubcategoriesFragment : BottomSheetDialogFragment(), ISearchSubcategoriesView {

    private val presenter = SearchSubcategoriesPresenter(this, SearchSubcategoriesModel())
    private val adapter = SearchSubcategoriesAdapter(presenter)
    private var categories = Bundle()
    private var categoryId = 0
    private var isSave = true

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            arguments = savedInstanceState
        }
        return inflater.inflate(R.layout.dialog_subcategories, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (!presenter.isSetView())
            presenter.attachView(this)

        if (arguments != null) {
            subcategoriesCategoryTitle.text = String.format(
                context!!.resources.getString(R.string.category_title),
                arguments!!.get("categoryName")!!.toString()
            )
            categoryId = arguments!!.get("categoryId")!!.toString().toInt()
            presenter.getSubcategories(context!!, categoryId)
            categories = arguments!!
            categories.remove("categoryId")
            categories.remove("categoryName")
        }
        subcategoriesCategoryTitle.setOnStateChangedListener { _, newState ->
            if (newState != null)
                if (newState)
                    adapter.changeCheckBoxes(true)
                else
                    adapter.changeCheckBoxes(false)
        }

        subcategoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        subcategoriesRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(subcategoriesRecyclerView, false)
        subcategoriesBtnClose.setOnClickListener {
            isSave = false
            dismiss()
        }
        subcategoriesAcceptButton.setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("categoryName", arguments!!.get("categoryName")!!.toString())
        outState.putString("categoryId", arguments!!.get("categoryId")!!.toString())
        categories.putIntegerArrayList("category$categoryId", adapter.getIsCheckedList())
        var cats = categories.getIntegerArrayList("categories")
        if (cats == null)
            cats = arrayListOf(categoryId)
        else
            if (!cats.contains(categoryId))
                cats.add(categoryId)
        categories.putIntegerArrayList("categories", cats)
        outState.putAll(categories)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        if (activity != null && isSave) {
            val searchFragment =
                activity!!.supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment) as SearchFragment
            val checkedList =adapter.getIsCheckedList()
            var cats = categories.getIntegerArrayList("categories")
            if(checkedList.isEmpty()) {
                categories.remove("category$categoryId")
                if (cats != null)
                    if (cats.contains(categoryId))
                        cats.remove(categoryId)
            } else {
                categories.putIntegerArrayList("category$categoryId", checkedList)
                if (cats == null)
                    cats = arrayListOf(categoryId)
                else
                    if (!cats.contains(categoryId))
                        cats.add(categoryId)
            }
            categories.putIntegerArrayList("categories", cats)
            searchFragment.setCategories(categories)
        }
        super.onDismiss(dialog)
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showSubcategories(subcategories: List<SubcategoryResponse>) {
        toggleContentPB(false)
        adapter.onDataChange(subcategories, categoryId, categories)
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            subcategoriesPB.visibility = View.VISIBLE
            subcategoriesRecyclerView.visibility = View.GONE
        } else {
            subcategoriesPB.visibility = View.GONE
            subcategoriesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun changeCheckBox(state: Int) {
        when (state) {
            0 -> {
                subcategoriesCategoryTitle.isIndeterminate = false
                subcategoriesCategoryTitle.isChecked = false
            }
            1 -> {
                subcategoriesCategoryTitle.isIndeterminate = false
                subcategoriesCategoryTitle.isChecked = true
            }
            else -> subcategoriesCategoryTitle.isIndeterminate = true
        }
    }
}