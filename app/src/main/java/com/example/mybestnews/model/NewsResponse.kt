package com.example.mybestnews.model

data class Article(
    val title: String,
    val body: String?,
    val url: String,
    val source: Source?,
    val date: String,
    val image: String
)

data class ArticlesWrapper(
    val results: List<Article>
)

data class NewsResponse(
    val articles: ArticlesWrapper
)

data class ArticlesRequest(
    val apiKey: String = "86527cc2-7ace-46e5-a855-74b4f517a2a9",
    val keyword: List<String>,
    val lang: String,
    val page: Int,
    val pageSize: Int,
    val keywordOper: String
)

data class Source(
    val uri: String?,
    val title: String?
)