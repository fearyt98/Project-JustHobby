package com.aurimteam.justhobby.user.main.home.user_bookmarks

import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import java.sql.Timestamp

class UserBookmarksModel : IUserBookmarksModel {
    interface OnFinishedListener {
        fun onResultSuccess(bookmarks: List<CourseResponse>)  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun getUserBookmarksData(OnFinishedListener: OnFinishedListener) {
        val bookmarks: List<CourseResponse> = listOf(
            CourseResponse(
                "sport",
                0,
                "Восточный брейк",
                "Drive - школа зажигательных танцев",
                "пр. Ленина 286, д. 12",
                IdentifierResponse("sport", 100),
                IdentifierResponse("Жиг", 101),
                Timestamp(100),
                Timestamp(200)
            ),
            CourseResponse(
                "dance",
                0,
                "Восточный базар",
                "Drive - школа зажигательных танцев",
                "пр. Ленина 26, д. 13",
                IdentifierResponse("sport", 100),
                IdentifierResponse("Жиг", 101),
                Timestamp(100),
                Timestamp(200)
            )
        )
        OnFinishedListener.onResultSuccess(bookmarks)
    }
}