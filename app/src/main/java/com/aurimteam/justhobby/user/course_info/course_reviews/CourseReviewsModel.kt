package com.aurimteam.justhobby.user.course_info.course_reviews

import com.aurimteam.justhobby.response.CourseReviewsResponse

class CourseReviewsModel : ICourseReviewsModel {
    interface OnFinishedListener {
        fun onResultSuccess(courseReviews: List<CourseReviewsResponse>)
        fun onResultFail()
    }

    override fun getCourseReviewsData(onFinishedListener: OnFinishedListener) {
        val courseReviews: List<CourseReviewsResponse> = listOf(
            CourseReviewsResponse(0, "Петр Андреевич", 5f, "Все просто отлично"),
            CourseReviewsResponse(0, "Петр Андреевич", 4f, "Все просто отлично"),
            CourseReviewsResponse(0, "Петр Андреевич", 3f, "Все просто ужасно"),
            CourseReviewsResponse(0, "Петр Андреевич", 2f, "Почти смогли"),
            CourseReviewsResponse(0, "Петр Андреевич", 1f, "Смогли!!!")
        )
        onFinishedListener.onResultSuccess(courseReviews)
    }
}