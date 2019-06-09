package com.aurimteam.justhobby.user.company_info.company_info

import com.aurimteam.justhobby.response.CompanyResponseOneR
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse

interface ICompanyInfoView {
    fun showCompanyCourses(courses: List<CourseResponseR>, included: IncludedResponse?)
    fun bindCompanyInfo(company: CompanyResponseOneR)
    fun showMessage(message: String?)
    fun toggleContentPB(isVisiblePB: Boolean)
    fun deletedUserBookmark(position: Int)
    fun addedUserBookmark(position: Int)
}