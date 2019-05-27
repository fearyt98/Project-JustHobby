package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import com.aurimteam.justhobby.Response.GroupResponse

interface ICourseGroupsView{
    fun showCourseAllGroupse(courseAllGroups: List<GroupResponse>)
}