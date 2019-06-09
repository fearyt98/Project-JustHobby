package com.aurimteam.justhobby.user.company_info.company_courses

interface ICompanyCoursesModel {
    fun getCompanyCoursesData(token: String, onFinishedListener: CompanyCoursesModel.OnFinishedListener)
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: CompanyCoursesModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: CompanyCoursesModel.OnFinishedListener)
}