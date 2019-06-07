package com.aurimteam.justhobby.user.main.home.user_groups

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import kotlinx.android.synthetic.main.card_user_group.view.*

class UserGroupsAdapter(private val presenter: UserGroupsPresenter) : RecyclerView.Adapter<UserGroupsHolder>() {

    private val userGroupsList: MutableList<GroupResponse> = mutableListOf()
    private val courseIncludedList: MutableMap<Long, CourseResponseR> = mutableMapOf()
    private val companyIncludedList: MutableMap<Long, CompanyResponse> = mutableMapOf()
    private val isClickedList: MutableList<Boolean> = mutableListOf()
    override fun getItemCount(): Int = userGroupsList.size
    override fun onBindViewHolder(holder: UserGroupsHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.toogleTimetable(
                userGroupsList[position].relationships.timetable_near,
                isClickedList[position]
            )
    }

    override fun onBindViewHolder(holder: UserGroupsHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val item = userGroupsList[position]
        val itemCourse = courseIncludedList[item.relationships.course.id]!!
        val itemCompany = companyIncludedList[itemCourse.relationships.company.id]!!
        holder.bind(
            itemCourse.attributes.title,
            item.attributes.title,
            itemCompany.attributes.title,
            itemCourse.attributes.address,
            item.attributes.teacher,
            item.relationships.timetable_near,
            isClickedList[position]
        )
        holder.itemView.cardUserGroupDays.setOnClickListener {
            if (item.relationships.timetable_near.size > 1)
                if (!isClickedList[position]) {
                    setIsClicked(position, true)
                    notifyItemChanged(position, listOf(1))
                } else {
                    setIsClicked(position, false)
                    notifyItemChanged(position, listOf(1))
                }
        }
        holder.itemView.cardUserGroupCenter.setOnClickListener { detailInfoCourse(manager, itemCourse, itemCompany) }
        holder.itemView.userGroupsDeleteBtn.setOnClickListener {
            deleteGroup(
                holder,
                item.id,
                position
            )
        }
    }

    private fun setIsClicked(position: Int, value: Boolean) {
        isClickedList[position] = value
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserGroupsHolder =
        UserGroupsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_user_group, parent, false)
        )

    fun onDataChange(userGroups: List<GroupResponse>, included: IncludedResponse) {
        userGroupsList.clear()
        userGroupsList.addAll(userGroups)
        courseIncludedList.clear()
        companyIncludedList.clear()
        if (included.courses != null)
            for (item in included.courses)
                courseIncludedList[item.id] = item
        if (included.companies != null)
            for (item in included.companies)
                companyIncludedList[item.id] = item
        for (item in userGroupsList)
            isClickedList.add(userGroupsList.indexOf(item), false)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse(fm: FragmentManager, course: CourseResponseR, company: CompanyResponse) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseInfoFragment())
            .commit()
    }

    private fun deleteGroup(holder: UserGroupsHolder, groupId: Long, position: Int) {
        presenter.deleteUserGroup(holder.itemView.context, groupId, position)
    }

    fun removeItem(position: Int) {
        userGroupsList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userGroupsList.count())
    }
}