package com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity

import com.aurimteam.justhobby.Response.GroupResponse

interface ICourseInfoView {
    fun showCourseGroups(groups: List<GroupResponse>)
}