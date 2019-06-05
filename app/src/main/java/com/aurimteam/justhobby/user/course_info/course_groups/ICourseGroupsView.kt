package com.aurimteam.justhobby.user.course_info.course_groups

import com.aurimteam.justhobby.response.GroupResponse

interface ICourseGroupsView{
    fun showCourseAllGroupse(courseAllGroups: List<GroupResponse>)
}