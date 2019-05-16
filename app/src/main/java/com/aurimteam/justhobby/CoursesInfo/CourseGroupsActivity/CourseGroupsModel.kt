package com.aurimteam.justhobby.CoursesInfo.CourseGroupsActivity

class CourseGroupsModel : ICourseGroupsModel {
    interface onFinishedListener {
        fun onResultSucces()
        fun onResultFail()
    }
}