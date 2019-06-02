package com.aurimteam.justhobby.CoursesInfo.CourseInfoActivity

import com.aurimteam.justhobby.Response.GroupResponse

interface ICourseInfoView {
    fun showCourseGroups(groups: List<GroupResponse>)
}