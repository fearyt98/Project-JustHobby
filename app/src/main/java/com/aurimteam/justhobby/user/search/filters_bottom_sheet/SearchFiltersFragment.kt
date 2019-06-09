package com.aurimteam.justhobby.user.search.filters_bottom_sheet

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.aurimteam.justhobby.R

class SearchFiltersFragment : BottomSheetDialogFragment(), ISearchFiltersView {

    private val presenter = SearchFiltersPresenter(this, SearchFiltersModel())
    private var filters: Bundle = Bundle()
    private var filtersClickedMap = mutableMapOf<String, Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_filters, container, false)
        view.findViewById<TextView>(R.id.resetBtn).setOnClickListener {
            resetFilters()
        }
        view.findViewById<CheckBox>(R.id.sortNear).setOnClickListener {
            addFilter("sortNear", view, R.id.sortNear)
        }
        view.findViewById<CheckBox>(R.id.sortPrice).setOnClickListener {
            addFilter("sortPrice", view, R.id.sortPrice)
        }
        view.findViewById<CheckBox>(R.id.sortRating).setOnClickListener {
            addFilter("sortRating", view, R.id.sortRating)
        }
        view.findViewById<CheckBox>(R.id.sexAny).setOnClickListener {
            addFilter("sexAny", view, R.id.sexAny)
        }
        view.findViewById<CheckBox>(R.id.sexMan).setOnClickListener {
            addFilter("sexMan", view, R.id.sexMan)
        }
        view.findViewById<CheckBox>(R.id.sexWoman).setOnClickListener {
            addFilter("sexWoman", view, R.id.sexWoman)
        }
        view.findViewById<CheckBox>(R.id.filterDayMonday).setOnClickListener {
            addFilter("filterDayMonday", view, R.id.filterDayMonday)
        }
        view.findViewById<CheckBox>(R.id.filterDayTuesday).setOnClickListener {
            addFilter("filterDayTuesday", view, R.id.filterDayTuesday)
        }
        view.findViewById<CheckBox>(R.id.filterDayWednesday).setOnClickListener {
            addFilter("filterDayWednesday", view, R.id.filterDayWednesday)
        }
        view.findViewById<CheckBox>(R.id.filterDayThursday).setOnClickListener {
            addFilter("filterDayThursday", view, R.id.filterDayThursday)
        }
        view.findViewById<CheckBox>(R.id.filterDayFriday).setOnClickListener {
            addFilter("filterDayFriday", view, R.id.filterDayFriday)
        }
        view.findViewById<CheckBox>(R.id.filterDaySaturday).setOnClickListener {
            addFilter("filterDaySaturday", view, R.id.filterDaySaturday)
        }
        view.findViewById<CheckBox>(R.id.filterDaySunday).setOnClickListener {
            addFilter("filterDaySunday", view, R.id.filterDaySunday)
        }
        view.findViewById<CheckBox>(R.id.applyAny).setOnClickListener {
            addFilter("applyAny", view, R.id.applyAny)
        }
        view.findViewById<CheckBox>(R.id.applyGo).setOnClickListener {
            addFilter("applyGo", view, R.id.applyGo)
        }
        view.findViewById<CheckBox>(R.id.applyNotGo).setOnClickListener {
            addFilter("applyNotGo", view, R.id.applyNotGo)
        }
        view.findViewById<Button>(R.id.filtersAcceptButton).setOnClickListener {
            sendChosenFilters()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        filters.putBoolean("sortNear", false)
        filtersClickedMap["sortNear"] = false

        filters.putBoolean("sortPrice", false)
        filtersClickedMap["sortPrice"] = false

        filters.putBoolean("sortRating", false)
        filtersClickedMap["sortRating"] = false

        filters.putBoolean("sexAny", false)
        filtersClickedMap["sexAny"] = false

        filters.putBoolean("sexMan", false)
        filtersClickedMap["sexMan"] = false

        filters.putBoolean("sexWoman", false)
        filtersClickedMap["sexWoman"] = false

        filters.putBoolean("filterDayMonday", false)
        filtersClickedMap["filterDayMonday"] = false

        filters.putBoolean("filterDayTuesday", false)
        filtersClickedMap["filterDayTuesday"] = false

        filters.putBoolean("filterDayWednesday", false)
        filtersClickedMap["filterDayWednesday"] = false

        filters.putBoolean("filterDayThursday", false)
        filtersClickedMap["filterDayThursday"] = false

        filters.putBoolean("filterDayFriday", false)
        filtersClickedMap["filterDayFriday"] = false

        filters.putBoolean("filterDaySaturday", false)
        filtersClickedMap["filterDaySaturday"] = false

        filters.putBoolean("filterDaySunday", false)
        filtersClickedMap["filterDaySunday"] = false

        filters.putBoolean("applyAny", false)
        filtersClickedMap["applyAny"] = false

        filters.putBoolean("applyGo", false)
        filtersClickedMap["applyGo"] = false

        filters.putBoolean("applyNotGo", false)
        filtersClickedMap["applyNotGo"] = false
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun addFilter(type: String, view: View, typeId: Int) {
        if (filtersClickedMap[type] == false) {
            filtersClickedMap[type] = true
            filters.putBoolean(type, true)
            view.findViewById<CheckBox>(typeId).isChecked = true
        } else if (filtersClickedMap[type] == true) {
            filtersClickedMap[type] = false
            filters.putBoolean(type, false)
            view.findViewById<CheckBox>(typeId).isChecked = false
        }
        if (type == "sortNear" || type == "sortRating" || type == "sortPrice") {
            if (filtersClickedMap["sortRating"] == true) {
                filtersClickedMap["sortNear"] = false
                filtersClickedMap["sortPrice"] = false
                view.findViewById<CheckBox>(R.id.sortNear).isChecked = false
                view.findViewById<CheckBox>(R.id.sortPrice).isChecked = false
                filters.putBoolean("sortNear", false)
                filters.putBoolean("sortPrice", false)
            } else if (filtersClickedMap["sortNear"] == true) {
                filtersClickedMap["sortRating"] = false
                filtersClickedMap["sortPrice"] = false
                view.findViewById<CheckBox>(R.id.sortRating).isChecked = false
                view.findViewById<CheckBox>(R.id.sortPrice).isChecked = false
                filters.putBoolean("sortRating", false)
                filters.putBoolean("sortPrice", false)
            } else if (filtersClickedMap["sortPrice"] == true) {
                filtersClickedMap["sortRating"] = false
                filtersClickedMap["sortNear"] = false
                view.findViewById<CheckBox>(R.id.sortRating).isChecked = false
                view.findViewById<CheckBox>(R.id.sortNear).isChecked = false
                filters.putBoolean("sortRating", false)
                filters.putBoolean("sortNear", false)
            }
        } else if (type == "sexAny" || type == "sexMan" || type == "sexWoman") {
            if (filtersClickedMap["sexAny"] == true && filtersClickedMap["sexMan"] == true) {
                filtersClickedMap["sexWoman"] = false
                view.findViewById<CheckBox>(R.id.sexWoman).isChecked = false
            } else if (filtersClickedMap["sexAny"] == true && filtersClickedMap["sexWoman"] == true) {
                filtersClickedMap["sexMan"] = false
                view.findViewById<CheckBox>(R.id.sexMan).isChecked = false
            } else if (filtersClickedMap["sexMan"] == true) {
                filtersClickedMap["sexWoman"] = false
                view.findViewById<CheckBox>(R.id.sexWoman).isChecked = false
            } else if (filtersClickedMap["sexWoman"] == true) {
                filtersClickedMap["sexMan"] = false
                view.findViewById<CheckBox>(R.id.sexMan).isChecked = false
            }
        } else if (type == "applyAny" || type == "applyGo" || type == "applyNotGo") {
            if (filtersClickedMap["applyAny"] == true) {
                filtersClickedMap["applyGo"] = false
                filtersClickedMap["applyNotGo"] = false
                view.findViewById<CheckBox>(R.id.applyGo).isChecked = false
                view.findViewById<CheckBox>(R.id.applyNotGo).isChecked = false
                filters.putBoolean("applyGo", false)
                filters.putBoolean("applyNotGo", false)
            } else if (filtersClickedMap["applyGo"] == true) {
                filtersClickedMap["applyAny"] = false
                filtersClickedMap["applyNotGo"] = false
                view.findViewById<CheckBox>(R.id.applyAny).isChecked = false
                view.findViewById<CheckBox>(R.id.applyNotGo).isChecked = false
                filters.putBoolean("applyAny", false)
                filters.putBoolean("applyNotGo", false)
            } else if (filtersClickedMap["applyNotGo"] == true) {
                filtersClickedMap["applyGo"] = false
                filtersClickedMap["applyAny"] = false
                view.findViewById<CheckBox>(R.id.applyAny).isChecked = false
                view.findViewById<CheckBox>(R.id.applyGo).isChecked = false
                filters.putBoolean("applyAny", false)
                filters.putBoolean("applyGo", false)
            }
        }
    }

    private fun resetFilters() {

    }

    private fun sendChosenFilters() {
        presenter.sendChosenFilters()
    }
}