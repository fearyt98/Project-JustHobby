package com.aurimteam.justhobby.user.course_info.course_review_new

interface ICourseReviewNewView {
    fun showMessage(message: String?)
    fun showError(strError: String)
    fun hideErrors()
    fun back()
}