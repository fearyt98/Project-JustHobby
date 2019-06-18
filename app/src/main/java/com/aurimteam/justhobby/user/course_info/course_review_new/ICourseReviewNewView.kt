package com.aurimteam.justhobby.user.course_info.course_review_new

interface ICourseReviewNewView {
    fun showMessage(message: String?, isImportant: Boolean = false)
    fun showError(strError: String)
    fun hideErrors()
    fun back()
}