package com.iyanuoluwa.inews.data.model

enum class Category (val categoryName: String) {

    SPORTS("sports"),
    BUZZ("buzz"),
    BUSINESS("business"),
    TOP_HEADLINES("top-headlines"),
    TECH("tech");

    companion object {
        fun fromCategoryName(categoryName: String): Category =
            values().find { categoryName == it.categoryName } ?: SPORTS
    }
}