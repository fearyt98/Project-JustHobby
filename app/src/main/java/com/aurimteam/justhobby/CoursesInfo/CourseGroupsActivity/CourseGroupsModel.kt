package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

import com.aurimteam.justhobby.Response.GroupResponse

class CourseGroupsModel : ICourseGroupsModel {
    interface onFinishedListener {
        fun onResultSuccess(courseAllGroups: List<GroupResponse>)
        fun onResultFail()
    }

    override fun getCourseAllGroupsData(onFinishedListener: onFinishedListener) {
        val courseGroups: List<GroupResponse> = listOf(
            GroupResponse(1, "Начинающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(2, "Продолжающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(3, "ГГ", "Виктория Алексеевна", "500Р занятие")
        )
        onFinishedListener.onResultSuccess(courseGroups)
    }
}