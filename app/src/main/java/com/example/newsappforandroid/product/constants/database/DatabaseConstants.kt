package com.example.newsappforandroid.product.constants.database

object DatabaseConstants {
    const val NEWS_DATABASE = "news_db"
    const val ARTICLES_TABLE = "articles"
    const val ARTICLES_TITLE = "title"
    const val SOURCES_TABLE = "sources"

    object ArticleSourceForeignKeys {
        const val PARENT_KEY = "sourceId"
        const val CHILD_KEY = "sourceId"
    }
}