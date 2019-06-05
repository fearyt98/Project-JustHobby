package com.aurimteam.justhobby.user.course_info.course_groups

import com.aurimteam.justhobby.response.GroupResponse

class CourseGroupsModel : ICourseGroupsModel {
    interface OnFinishedListener {
        fun onResultSuccess(courseAllGroups: List<GroupResponse>)
        fun onResultFail()
    }

    override fun getCourseAllGroupsData(onFinishedListener: OnFinishedListener) {
        val courseGroups: List<GroupResponse> = listOf(
            GroupResponse(1, "Начинающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(2, "Продолжающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(3, "ГГ", "Виктория Алексеевна", "500Р занятие")
        )
        onFinishedListener.onResultSuccess(courseGroups)
    }
}