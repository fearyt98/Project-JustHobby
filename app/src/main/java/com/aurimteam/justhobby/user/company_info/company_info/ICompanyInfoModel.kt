package com.aurimteam.justhobby.user.company_info.company_info

interface ICompanyInfoModel {
    fun getCompanyCoursesData(
        token: String,
        companyId: Long,
        lat: Float?,
        lon: Float?,
        onFinishedListener: CompanyInfoModel.OnFinishedListener
    )
    fun getCompanyData(token: String, companyId: Long, onFinishedListener: CompanyInfoModel.OnFinishedListener)
    fun deleteUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: CompanyInfoModel.OnFinishedListener)
    fun addUserBookmark(token: String, courseId: Long, position: Int, onFinishedListener: CompanyInfoModel.OnFinishedListener)

}