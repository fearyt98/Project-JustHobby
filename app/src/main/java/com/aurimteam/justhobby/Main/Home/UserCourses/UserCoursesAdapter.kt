package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.UserCourseResponse
import com.aurimteam.justhobby.Response.UserCourseTimetableResponse
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*

class UserCoursesAdapter : RecyclerView.Adapter<UserCoursesHolder>() {

    private var isClicked = false
    private val userCoursesList: MutableList<UserCourseResponse> = mutableListOf()
    override fun getItemCount(): Int = userCoursesList.size
    override fun onBindViewHolder(holder: UserCoursesHolder, position: Int) {
        holder.bind(
            userCoursesList[position].title,
            userCoursesList[position].description,
            userCoursesList[position].address,
            userCoursesList[position].tutor
        )
        showFirstDay(userCoursesList[position].timeTable[0], holder)
        holder.itemView.daysOfWeek.setOnClickListener {
            if (!isClicked) showAllDays(position, holder)
            else hideAllDays(position, holder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserCoursesHolder =
        UserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_user_course, parent, false)
        )

    fun onDataChange(userCourses: List<UserCourseResponse>) {
        this.userCoursesList.clear()
        this.userCoursesList.addAll(userCourses)
        notifyDataSetChanged()
    }

    private fun showAllDays(position: Int, holder: UserCoursesHolder) {
        isClicked = true
        holder.itemView.mondayWeek.visibility = View.GONE
        holder.itemView.tuesdayWeek.visibility = View.GONE
        holder.itemView.wednesdayWeek.visibility = View.GONE
        holder.itemView.thursdayWeek.visibility = View.GONE
        holder.itemView.fridayWeek.visibility = View.GONE
        holder.itemView.saturdayWeek.visibility = View.GONE
        holder.itemView.sundayWeek.visibility = View.GONE
        for (item in userCoursesList[position].timeTable) {
            when (item.dayWeek) {
                "ПН" -> {
                    holder.itemView.mondayStartTime.text = item.timeStart
                    holder.itemView.mondayEndTime.text = item.timeEnd
                    holder.itemView.mondayWeek.visibility = View.VISIBLE
                }
                "ВТ" -> {
                    holder.itemView.tuesdayStartTime.text = item.timeStart
                    holder.itemView.tuesdayEndTime.text = item.timeEnd
                    holder.itemView.tuesdayWeek.visibility = View.VISIBLE
                }
                "СР" -> {
                    holder.itemView.wednesdayStartTime.text = item.timeStart
                    holder.itemView.wednesdayEndTime.text = item.timeEnd
                    holder.itemView.wednesdayWeek.visibility = View.VISIBLE
                }
                "ЧТ" -> {
                    holder.itemView.thursdayStartTime.text = item.timeStart
                    holder.itemView.thursdayEndTime.text = item.timeEnd
                    holder.itemView.thursdayWeek.visibility = View.VISIBLE
                }
                "ПТ" -> {
                    holder.itemView.fridayStartTime.text = item.timeStart
                    holder.itemView.fridayEndTime.text = item.timeEnd
                    holder.itemView.fridayWeek.visibility = View.VISIBLE
                }
                "СБ" -> {
                    holder.itemView.saturdayStartTime.text = item.timeStart
                    holder.itemView.saturdayEndTime.text = item.timeEnd
                    holder.itemView.saturdayWeek.visibility = View.VISIBLE
                }
                "ВС" -> {
                    holder.itemView.sundayStartTime.text = item.timeStart
                    holder.itemView.sundayEndTime.text = item.timeEnd
                    holder.itemView.sundayWeek.visibility = View.VISIBLE
                }
            }
        }
        notifyItemChanged(position)
    }

    private fun hideAllDays(position: Int, holder: UserCoursesHolder) {
        isClicked = false
        holder.itemView.mondayWeek.visibility = View.GONE
        holder.itemView.tuesdayWeek.visibility = View.GONE
        holder.itemView.wednesdayWeek.visibility = View.GONE
        holder.itemView.thursdayWeek.visibility = View.GONE
        holder.itemView.fridayWeek.visibility = View.GONE
        holder.itemView.saturdayWeek.visibility = View.GONE
        holder.itemView.sundayWeek.visibility = View.GONE
        showFirstDay(userCoursesList[position].timeTable[0], holder)
        notifyItemChanged(position)
    }

    private fun showFirstDay(day: UserCourseTimetableResponse, holder: UserCoursesHolder) {
        when (day.dayWeek) {
            "ПН" -> {
                holder.itemView.mondayStartTime.text = day.timeStart
                holder.itemView.mondayEndTime.text = day.timeEnd
                holder.itemView.mondayWeek.visibility = View.VISIBLE
            }
            "ВТ" -> {
                holder.itemView.tuesdayStartTime.text = day.timeStart
                holder.itemView.tuesdayEndTime.text = day.timeEnd
                holder.itemView.tuesdayWeek.visibility = View.VISIBLE
            }
            "СР" -> {
                holder.itemView.wednesdayStartTime.text = day.timeStart
                holder.itemView.wednesdayEndTime.text = day.timeEnd
                holder.itemView.wednesdayWeek.visibility = View.VISIBLE
            }
            "ЧТ" -> {
                holder.itemView.thursdayStartTime.text = day.timeStart
                holder.itemView.thursdayEndTime.text = day.timeEnd
                holder.itemView.thursdayWeek.visibility = View.VISIBLE
            }
            "ПТ" -> {
                holder.itemView.fridayStartTime.text = day.timeStart
                holder.itemView.fridayEndTime.text = day.timeEnd
                holder.itemView.fridayWeek.visibility = View.VISIBLE
            }
            "СБ" -> {
                holder.itemView.saturdayStartTime.text = day.timeStart
                holder.itemView.saturdayEndTime.text = day.timeEnd
                holder.itemView.saturdayWeek.visibility = View.VISIBLE
            }
            "ВС" -> {
                holder.itemView.sundayStartTime.text = day.timeStart
                holder.itemView.sundayEndTime.text = day.timeEnd
                holder.itemView.sundayWeek.visibility = View.VISIBLE
            }
        }
    }
}