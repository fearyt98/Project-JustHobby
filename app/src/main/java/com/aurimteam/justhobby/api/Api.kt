package com.aurimteam.justhobby.api

import com.aurimteam.justhobby.response.*
import com.aurimteam.justhobby.response_body.*
import retrofit2.Call
import retrofit2.http.*
import okhttp3.RequestBody
import okhttp3.MultipartBody

interface Api {
    @Headers("Accept: application/json")
    @POST("login")
    fun login(
        @Body loginBody: LoginBody
    ): Call<TokenResponse>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    fun logout(
        @Body tokenBody: TokenBody
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @POST("register")
    fun registry(
        @Body registryBody: RegistryBody
    ): Call<TokenResponse>

    @Headers("Accept: application/json")
    @PATCH("user")
    fun updateUser(
        @Body updateUserBody: UpdateUserBody
    ): Call<UserResponse>

    @Headers("Accept: application/json")
    @GET("user/notifications/create")
    fun checkUserNewNotify(
        @Query("token") token: String
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @GET("user/notifications")
    fun getUserNotify(
        @Query("token") token: String,
        @Query("is_showed") isShowed: Boolean?
    ): Call<NotificationsResponse>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", path = "user/notifications", hasBody = true)
    fun deleteAllNotify(
        @Body tokenBody: TokenBody
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @PATCH("user")
    fun updateAllUserInfo(
        @Body updateUserAllBody: UpdateUserAllBody
    ): Call<UserResponse>

    @Multipart
    @Headers("Accept: application/json")
    @POST("user")
    fun uploadUserImage(
        @Query("token") token: String,
        @Part("name") requestBody: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<UserResponse>

    @Headers("Accept: application/json")
    @POST("forgot")
    fun recallEmail(
        @Body recoveryBody: RecoveryBody
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @GET("price")
    fun getPriceRange(
        @Query("token") token: String
    ): Call<PriceRangeResponse>

    @Headers("Accept: application/json")
    @GET("categories")
    fun getCategories(
        @Query("token") token: String,
        @Query("page[size]") page_size: Int,
        @Query("page[number]") page_number: Int
    ): Call<CategoriesResponse>

    @Headers("Accept: application/json")
    @GET("categories/{category_id}/subcategories")
    fun getSubcategories(
        @Path("category_id") categoryId: Int,
        @Query("token") token: String
    ): Call<SubcategoriesResponse>

    @Headers("Accept: application/json")
    @GET("user/timeline/near_day")
    fun getNearDayTimeline(
        @Query("token") token: String,
        @Query("next") next: Boolean?
    ): Call<TimelineNearDayResponse>

    @Headers("Accept: application/json")
    @GET("user/timeline")
    fun getTimeline(
        @Query("token") token: String,
        @Query("date") date: String
    ): Call<TimelineResponse>

    @Headers("Accept: application/json")
    @GET("user")
    fun getUser(
        @Query("token") token: String
    ): Call<UserResponse>

    //Get bookmarks, delete bookmark, add bookmark

    @Headers("Accept: application/json")
    @GET("user/bookmarks?include[course][]=company")
    fun getUserBookmarks(
        @Query("token") token: String,
        @Query("geo[lat]") lat: Float?,
        @Query("geo[lon]") lon: Float?
    ): Call<CoursesResponse>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", path = "user/bookmarks/{course_id}", hasBody = true)
    fun deleteBookmark(
        @Body tokenBody: TokenBody,
        @Path("course_id") courseId: Long
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @POST("user/bookmarks")
    fun addBookmark(
        @Body bookmarkAddBody: BookmarkAddBody
    ): Call<StatusResponse>

    //Get user groups, delete user group, add user group

    @Headers("Accept: application/json")
    @GET("user/groups?include[group][]=course&include[group][]=timetable_near&include[course][]=company")
    fun getUserGroups(
        @Query("token") token: String
    ): Call<GroupsResponseI>

    @Headers("Accept: application/json")
    @HTTP(method = "DELETE", path = "user/groups/{group_id}", hasBody = true)
    fun deleteGroup(
        @Body tokenBody: TokenBody,
        @Path("group_id") groupId: Long
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @POST("user/groups")
    fun addGroup(
        @Body groupAddBody: GroupAddBody
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @GET("courses?page[size]=20&include[course][]=company&include[course][]=user")
    fun getCourses(
        @Query("filters[subcategories]") subcategories: String?,
        @Query("sort[price]") sortPrice: Int?,
        @Query("sort[rating]") sortRating: Int?,
        @Query("sort[length]") sortLength: Int?,
        @Query("filters[price_max]") priceMax: Int?,
        @Query("filters[price_min]") priceMin: Int?,
        @Query("filters[age_max]") ageMax: Int?,
        @Query("filters[age_min]") ageMin: Int?,
        @Query("filters[sex][]") sex1: Int?,
        @Query("filters[sex][]") sex2: Int?,
        @Query("filters[sex][]") sex3: Int?,
        @Query("filters[timetable][]") timetable1: Int?,
        @Query("filters[timetable][]") timetable2: Int?,
        @Query("filters[timetable][]") timetable3: Int?,
        @Query("filters[timetable][]") timetable4: Int?,
        @Query("filters[timetable][]") timetable5: Int?,
        @Query("filters[timetable][]") timetable6: Int?,
        @Query("filters[timetable][]") timetable7: Int?,
        @Query("filters[status]") status: Int?,
        @Query("q") query: String?,
        @Query("token") token: String,
        @Query("geo[lat]") lat: Float?,
        @Query("geo[lon]") lon: Float?
    ): Call<CoursesResponse>

    @Headers("Accept: application/json")
    @GET("courses?page[size]=10&include[course][]=company&include[course][]=user&sort[popular]=1")
    fun getPopularCourses(
        @Query("token") token: String,
        @Query("geo[lat]") lat: Float?,
        @Query("geo[lon]") lon: Float?
    ): Call<CoursesResponse>

    @Headers("Accept: application/json")
    @GET("courses?page[size]=10&include[course][]=company&include[course][]=user&sort[length]=1")
    fun getNearCourses(
        @Query("token") token: String,
        @Query("geo[lat]") lat: Float?,
        @Query("geo[lon]") lon: Float?
    ): Call<CoursesResponse>

    @Headers("Accept: application/json")
    @GET("courses/{course_id}?include[course][]=company&include[course][]=user&include[course][]=count_groups&include[course][]=count_reviews")
    fun getOneCourse(
        @Path("course_id") courseId: Long,
        @Query("token") token: String
    ): Call<CourseResponseOneR>

    @Headers("Accept: application/json")
    @GET("courses/{course_id}/groups?include[group][]=user&include[group][]=timetable")
    fun getGroupsOneCourse(
        @Path("course_id") courseId: Long,
        @Query("token") token: String
    ): Call<GroupsResponse>

    @Headers("Accept: application/json")
    @GET("courses/{course_id}/reviews")
    fun getReviewsOneCourse(
        @Path("course_id") courseId: Long,
        @Query("token") token: String
    ): Call<ReviewsResponse>

    @Headers("Accept: application/json")
    @POST("courses/{course_id}/review")
    fun addReviewOneCourse(
        @Path("course_id") courseId: Long,
        @Body reviewAddBody: ReviewAddBody
    ): Call<StatusResponse>

    @Headers("Accept: application/json")
    @GET("companies/{company_id}?include[company][]=count_courses&include[company][]=count_reviews")
    fun getOneCompany(
        @Path("company_id") companyId: Long,
        @Query("token") token: String
    ): Call<CompanyResponseOneR>

    @Headers("Accept: application/json")
    @GET("companies/{company_id}/courses?&include[course][]=company&include[courses][]=user")
    fun getCoursesOneCompany(
        @Path("company_id") companyId: Long,
        @Query("token") token: String,
        @Query("geo[lat]") lat: Float?,
        @Query("geo[lon]") lon: Float?
    ): Call<CoursesResponse>
}