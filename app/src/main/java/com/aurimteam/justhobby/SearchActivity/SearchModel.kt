package com.aurimteam.justhobby.SearchActivity

class SearchModel : ISearchModel {
    interface onFinishedListener {
        fun onResultSuccess(categories: List<String>)
        fun onResultSuccess(number: Int)
        fun onResultFail()
    }

    override fun getCategoriesData(onFinishedListener: onFinishedListener) {
        val categories: List<String> = listOf(
            "Спорт",
            "Языки",
            "Музыка",
            "IT",
            "Танцы",
            "Искусство и дизайн",
            "Ремесло",
            "Конструирование",
            "Словесность",
            "Интеллектуальные",
            "Мода и стиль",
            "Зрелищные искусства"
        )
        onFinishedListener.onResultSuccess(categories)
    }
}