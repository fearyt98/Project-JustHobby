package com.aurimteam.justhobby.user.search.filters_bottom_sheet

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.dialog_filters.*
import com.aurimteam.justhobby.user.search.search.SearchFragment

class SearchFiltersFragment : BottomSheetDialogFragment() {

    private var filters: Bundle = Bundle()
    private var filtersClickedMap = mutableMapOf<String, Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_filters, container, false)
        view.findViewById<TextView>(R.id.resetBtn).setOnClickListener {
            reset()
        }
        view.findViewById<CheckBox>(R.id.sortNear).setOnClickListener {
            changeFilter("sortNear", view, R.id.sortNear)
        }
        view.findViewById<CheckBox>(R.id.sortPrice).setOnClickListener {
            changeFilter("sortPrice", view, R.id.sortPrice)
        }
        view.findViewById<CheckBox>(R.id.sortRating).setOnClickListener {
            changeFilter("sortRating", view, R.id.sortRating)
        }
        view.findViewById<CheckBox>(R.id.sexAny).setOnClickListener {
            changeFilter("sexAny", view, R.id.sexAny)
        }
        view.findViewById<CheckBox>(R.id.sexMan).setOnClickListener {
            changeFilter("sexMan", view, R.id.sexMan)
        }
        view.findViewById<CheckBox>(R.id.sexWoman).setOnClickListener {
            changeFilter("sexWoman", view, R.id.sexWoman)
        }
        view.findViewById<CheckBox>(R.id.filterDayMonday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDayMonday)
        }
        view.findViewById<CheckBox>(R.id.filterDayTuesday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDayTuesday)
        }
        view.findViewById<CheckBox>(R.id.filterDayWednesday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDayWednesday)
        }
        view.findViewById<CheckBox>(R.id.filterDayThursday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDayThursday)
        }
        view.findViewById<CheckBox>(R.id.filterDayFriday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDayFriday)
        }
        view.findViewById<CheckBox>(R.id.filterDaySaturday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDaySaturday)
        }
        view.findViewById<CheckBox>(R.id.filterDaySunday).setOnClickListener {
            changeFilter("filterDays", view, R.id.filterDaySunday)
        }
        view.findViewById<CheckBox>(R.id.statusAny).setOnClickListener {
            changeFilter("statusAny", view, R.id.statusAny)
        }
        view.findViewById<CheckBox>(R.id.statusTrue).setOnClickListener {
            changeFilter("statusTrue", view, R.id.statusTrue)
        }
        view.findViewById<CheckBox>(R.id.statusFalse).setOnClickListener {
            changeFilter("statusFalse", view, R.id.statusFalse)
        }
        view.findViewById<Button>(R.id.filtersAcceptButton).setOnClickListener {
            dismiss()
        }
        if (savedInstanceState != null) {
            arguments = savedInstanceState
            savedInstanceState.clear()
        }
        return view
    }

    override fun onStart() {
        super.onStart()

        if (arguments != null)
            filters.putAll(arguments!!)
        if (!filters.containsKey("sortNear")) {
            resetFilters()
        }
        setFromArguments()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(filters)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        if (activity != null) {
            val searchFragment =
                activity!!.supportFragmentManager.findFragmentById(R.id.mainNavContainerFragment) as SearchFragment
            searchFragment.setFilters(filters)
        }
        super.onDismiss(dialog)
    }

    private fun setFromArguments() {
        setCheck("sortNear", view!!, R.id.sortNear)
        setCheck("sortPrice", view!!, R.id.sortPrice)
        setCheck("sortRating", view!!, R.id.sortRating)
        setCheck("sexAny", view!!, R.id.sexAny)
        setCheck("sexMan", view!!, R.id.sexMan)
        setCheck("sexWoman", view!!, R.id.sexWoman)
        setCheck("filterDays", view!!, R.id.filterDayMonday)
        setCheck("filterDays", view!!, R.id.filterDayTuesday)
        setCheck("filterDays", view!!, R.id.filterDayWednesday)
        setCheck("filterDays", view!!, R.id.filterDayThursday)
        setCheck("filterDays", view!!, R.id.filterDayFriday)
        setCheck("filterDays", view!!, R.id.filterDaySaturday)
        setCheck("filterDays", view!!, R.id.filterDaySunday)
        setCheck("statusAny", view!!, R.id.statusAny)
        setCheck("statusTrue", view!!, R.id.statusTrue)
        setCheck("statusFalse", view!!, R.id.statusFalse)
        setRange("cost")
        setRange("age")

    }

    private fun changeFilter(type: String, view: View, id: Int) {
        val typeId = if (type == "filterDays") "filterDays$id" else type
        if (filtersClickedMap[typeId] == false)
            if (type == "sortPrice")
                setSortPrice("sortPrice", view, R.id.sortPrice, 1)
            else
                setCheck(type, view, id, true)
        else {
            if (type == "sortPrice")
                if (filters.getInt(type) == 1)
                    setSortPrice(type, view, id, 2)
                else
                    setSortPrice(type, view, id, 1)
            else {
                if (
                    (type == "sortNear" && filtersClickedMap[type] == true) ||
                    (type == "sortRating" && filtersClickedMap[type] == true) ||
                    (type == "sexAny" && filtersClickedMap[type] == true) ||
                    (type == "statusAny" && filtersClickedMap[type] == true) ||
                    (type == "statusTrue" && filtersClickedMap[type] == true) ||
                    (type == "statusFalse" && filtersClickedMap[type] == true)
                )
                    setCheck(type, view, id, true)
                else
                    setCheck(type, view, id, false)
            }
        }
        if (type == "sortNear" || type == "sortRating" || type == "sortPrice") {
            when {
                filtersClickedMap["sortRating"] == true && type == "sortRating" -> {
                    setCheck("sortNear", view, R.id.sortNear, false)
                    setSortPrice("sortPrice", view, R.id.sortPrice, 0)
                }
                filtersClickedMap["sortNear"] == true && type == "sortNear" -> {
                    setCheck("sortRating", view, R.id.sortRating, false)
                    setSortPrice("sortPrice", view, R.id.sortPrice, 0)
                }
                filtersClickedMap["sortPrice"] == true && type == "sortPrice" -> {
                    setCheck("sortRating", view, R.id.sortRating, false)
                    setCheck("sortNear", view, R.id.sortNear, false)
                }
            }
        } else if (type == "sexAny" || type == "sexMan" || type == "sexWoman") {
            when {
                filtersClickedMap["sexMan"] == true && type == "sexMan" -> {
                    setCheck("sexWoman", view, R.id.sexWoman, false)
                }
                filtersClickedMap["sexWoman"] == true && type == "sexWoman" -> {
                    setCheck("sexMan", view, R.id.sexMan, false)
                }
            }
        } else if (type == "statusAny" || type == "statusTrue" || type == "statusFalse") {
            when {
                filtersClickedMap["statusAny"] == true && type == "statusAny" -> {
                    setCheck("statusTrue", view, R.id.statusTrue, false)
                    setCheck("statusFalse", view, R.id.statusFalse, false)
                }
                filtersClickedMap["statusTrue"] == true && type == "statusTrue" -> {
                    setCheck("statusAny", view, R.id.statusAny, false)
                    setCheck("statusFalse", view, R.id.statusFalse, false)
                }
                filtersClickedMap["statusFalse"] == true && type == "statusFalse" -> {
                    setCheck("statusAny", view, R.id.statusAny, false)
                    setCheck("statusTrue", view, R.id.statusTrue, false)
                }
            }
        }
    }

    private fun setCheck(type: String, view: View, id: Int, isCheck: Boolean) {
        if (type == "filterDays") {
            var days = filters.getString(type)
            if (days != null) {
                val daysArray = days.split(",")
                val daysArrayNew = mutableListOf<String>()
                for (day in daysArray)
                    if (day.toInt() != getIndexDay(id))
                        daysArrayNew.add(day)
                if (isCheck)
                    daysArrayNew.add(getIndexDay(id).toString())
                if (daysArrayNew.size != 0) {
                    days = daysArrayNew[0]
                    for (day in daysArrayNew)
                        if (daysArrayNew.indexOf(day) != 0)
                            days = "$days,$day"
                    filtersClickedMap["filterDays$id"] = isCheck
                    view.findViewById<CheckBox>(id).isChecked = isCheck
                    filters.putString(type, days)
                } else {
                    view.findViewById<CheckBox>(id).isChecked = !isCheck
                }
            }
        } else {
            filtersClickedMap[type] = isCheck
            view.findViewById<CheckBox>(id).isChecked = isCheck
            filters.putBoolean(type, isCheck)
        }
    }

    private fun setSortPrice(type: String, view: View, id: Int, check: Int) {
        if (check == 0) {
            filtersClickedMap[type] = false
            view.findViewById<CheckBox>(id).isChecked = false
            sortPriceImage.visibility = View.GONE
        } else {
            filtersClickedMap[type] = true
            view.findViewById<CheckBox>(id).isChecked = true
            sortPriceImage.visibility = View.VISIBLE
            if (check == 1) {
                sortPriceImage.setImageResource(R.drawable.ic_arrow_upward_24dp)
            } else {
                sortPriceImage.setImageResource(R.drawable.ic_arrow_downward_24dp)
            }
        }
        filters.putInt(type, check)
    }

    private fun setCheck(type: String, view: View, id: Int) {
        if (type == "filterDays") {
            val days = filters.getString(type)
            if (days != null) {
                val daysArray = days.split(",")
                var isSetDay = false
                for (day in daysArray)
                    if (day.toInt() == getIndexDay(id)) {
                        isSetDay = true
                        setCheck(type, view, id, true)
                    }
                if (!isSetDay)
                    setCheck(type, view, id, false)
            }
        } else if (type == "sortPrice") {
            setSortPrice(type, view, id, filters.getInt(type))
        } else
            setCheck(type, view, id, filters.getBoolean(type))
    }

    private fun getIndexDay(id: Int): Int {
        return when (id) {
            R.id.filterDayMonday -> 0
            R.id.filterDayTuesday -> 1
            R.id.filterDayWednesday -> 2
            R.id.filterDayThursday -> 3
            R.id.filterDayFriday -> 4
            R.id.filterDaySaturday -> 5
            R.id.filterDaySunday -> 6
            else -> 0
        }
    }

    private fun setRange(type: String, currentMin: Int, currentMax: Int, min: Int, max: Int) {
        var seekBar = filtersCostSeekBar
        var minTextView = costMin
        var maxTextView = costMax
        if (type == "age") {
            seekBar = filtersAgeSeekBar
            minTextView = ageMin
            maxTextView = ageMax
            seekBar.setSteps(5f)
        } else {
            seekBar.setSteps(10f)
        }
        minTextView.text = currentMin.toString()
        maxTextView.text = currentMax.toString()
        seekBar.setMinValue(min.toFloat())
        seekBar.setMaxValue(max.toFloat())
        seekBar.setMinStartValue(currentMin.toFloat())
        seekBar.setMaxStartValue(currentMax.toFloat()).apply()
        seekBar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            minTextView.text = minValue.toString()
            maxTextView.text = maxValue.toString()
            filters.putInt(type + "Min", minValue.toInt())
            filters.putInt(type + "Max", maxValue.toInt())
        }
    }

    private fun setRange(type: String) {
        setRange(
            type,
            filters.getInt(type + "Min"),
            filters.getInt(type + "Max"),
            filters.getInt(type + "MinAll"),
            filters.getInt(type + "MaxAll")
        )
    }

    private fun resetFilters() {
        filters.putBoolean("sortNear", false)
        filters.putBoolean("sortRating", true)
        filters.putInt("sortPrice", 0)
        filters.putBoolean("sexAny", true)
        filters.putBoolean("sexMan", false)
        filters.putBoolean("sexWoman", false)
        filters.putString("filterDays", "0,1,2,3,4,5,6")
        filters.putBoolean("statusAny", true)
        filters.putBoolean("statusTrue", false)
        filters.putBoolean("statusFalse", false)
        filters.putInt("ageMax", filters.getInt("ageMaxAll"))
        filters.putInt("ageMin", filters.getInt("ageMinAll"))
        filters.putInt("costMax", filters.getInt("costMaxAll"))
        filters.putInt("costMin", filters.getInt("costMinAll"))
        setFromArguments()
    }

    private fun reset() {
        resetFilters()
        dismiss()
    }
}