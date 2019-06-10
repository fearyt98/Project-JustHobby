package com.aurimteam.justhobby.response

class  NotificationsResponse(
    val data: List<NotificationResponse>,
    val included: IncludedResponse
)
class NotificationResponse(
    val type: String,
    val id: Long,
    val attributes: NotifyAttrResponse,
    val relationships: NotifyRelationshipsResponse
)
class NotifyAttrResponse(
    val text: String,
    val created_at: Long
)

class NotifyRelationshipsResponse(
    val course: IdentifierResponse
)
