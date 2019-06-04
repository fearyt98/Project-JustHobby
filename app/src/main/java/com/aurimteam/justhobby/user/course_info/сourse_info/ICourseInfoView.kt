package com.aurimteam.justhobby.user.course_info.сourse_info

import com.aurimteam.justhobby.response.GroupResponse

interface ICourseInfoView {
    fun showCourseGroups(groups: List<GroupResponse>)
}