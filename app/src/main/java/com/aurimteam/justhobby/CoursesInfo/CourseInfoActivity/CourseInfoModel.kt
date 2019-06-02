package com.aurimteam.justhobby.CoursesInfo.CourseInfoActivity

import com.aurimteam.justhobby.Response.GroupResponse

class CourseInfoModel : ICourseInfoModel {
    interface onFinishedListener {
        fun onResultSuccess(groups: List<GroupResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getCourseGroupsData(onFinishedListener: onFinishedListener) {
        val groups: List<GroupResponse> = listOf(
            GroupResponse(1, "Начинающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(2, "Продолжающие", "Виктория Алексеевна", "500Р занятие"),
            GroupResponse(3, "ГГ", "Виктория Алексеевна", "500Р занятие")
        )
        onFinishedListener.onResultSuccess(groups)
    }
}