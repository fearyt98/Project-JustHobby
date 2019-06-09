package com.aurimteam.justhobby.group

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.GroupResponse
import kotlinx.android.synthetic.main.card_group.view.*
import com.tooltip.Tooltip



class GroupAdapter(private val presenter: IGroupPresenter) : RecyclerView.Adapter<GroupHolder>() {

    private val groupsList: MutableList<GroupResponse> = mutableListOf()
    private val groupsOfUser: MutableList<Boolean> = mutableListOf()

    override fun getItemCount(): Int = groupsList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GroupHolder =
        GroupHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_group, parent, false)
        )

    override fun onBindViewHolder(holder: GroupHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.toggleBtnAddDelete(groupsOfUser[position])
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        val item = groupsList[position]
        holder.bind(
            item.attributes.title,
            item.attributes.description,
            item.attributes.teacher,
            item.attributes.status,
            0,//item.attributes.type_payment,
            item.attributes.price,
            item.attributes.sex,
            item.attributes.age_max,
            item.attributes.age_min,
            item.relationships.timetable,
            item.relationships.user
        )
        holder.itemView.cardGroupBtnAdd.setOnClickListener {
            addGroup(
                holder,
                groupsList[position].id,
                position
            )
        }
        holder.itemView.cardGroupBtnDelete.setOnClickListener {
            deleteGroup(
                holder,
                groupsList[position].id,
                position
            )
        }
        holder.itemView.cardGroupBtnInfo.setOnClickListener {
            Tooltip.Builder(holder.itemView.cardGroupBtnInfo)
                .setText(item.attributes.description)
                .setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grafit))
                .setCornerRadius(15f)
                .setTextAppearance(R.style.Caption1_Medium)
                .setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.whiteTop))
                .setArrow(R.drawable.tooltip_arrow)
                .setCancelable(true)
                .setDismissOnClick(true)
                .setGravity(Gravity.TOP)
                .setArrowHeight(R.dimen.design_tooltip_arrow_height)
                .setArrowWidth(R.dimen.design_tooltip_arrow_width)
                .setPadding(15f)
                .setMaxWidth(300)
                .show()
        }
    }

    fun onDataChange(groups: List<GroupResponse>) {
        this.groupsList.clear()
        groupsList.addAll(groups)
        for (item in groupsList)
            if (item.relationships.user != null)
                groupsOfUser.add(groupsList.indexOf(item), item.relationships.user)
            else
                groupsOfUser.add(groupsList.indexOf(item), false)
        notifyDataSetChanged()
    }

    fun deletedGroup(position: Int) {
        groupsOfUser[position] = false
        notifyItemChanged(position, listOf(1))
    }

    fun addedGroup(position: Int) {
        groupsOfUser[position] = true
        notifyItemChanged(position, listOf(1))
    }

    private fun deleteGroup(holder: GroupHolder, groupId: Long, position: Int) {
        presenter.deleteUserGroup(holder.itemView.context, groupId, position)
    }

    private fun addGroup(holder: GroupHolder, groupId: Long, position: Int) {
        presenter.addUserGroup(holder.itemView.context, groupId, position)
    }
}