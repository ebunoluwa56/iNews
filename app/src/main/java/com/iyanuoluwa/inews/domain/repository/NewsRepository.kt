package com.iyanuoluwa.inews.domain.repository

import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.data.model.NewsResponse
import com.iyanuoluwa.inews.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(category: Category) : Flow<Resource<NewsResponse>>

    suspend fun getOtherNews() : Flow<Resource<NewsResponse>>
}