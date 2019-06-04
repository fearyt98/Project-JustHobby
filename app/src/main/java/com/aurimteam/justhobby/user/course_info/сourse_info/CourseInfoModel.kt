package com.aurimteam.justhobby.user.course_info.сourse_info

import com.aurimteam.justhobby.response.GroupResponse

class CourseInfoModel : ICourseInfoModel {
    interface OnFinishedListener {
        fun onResultSuccess(groups: List<GroupResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getCourseGroupsData(onFinishedListener: OnFinishedListener) {
        val groups: List<GroupResponse> = listOf(
            GroupResponse(1, "Начинающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(2, "Продолжающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(3, "ГГ", "Виктория Алексеевна", "500Р занятие")
        )
        onFinishedListener.onResultSuccess(groups)
    }
}