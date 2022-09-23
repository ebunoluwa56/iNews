package com.iyanuoluwa.inews.data.repository

import android.util.Log
import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.data.remote.NewsApi
import com.iyanuoluwa.inews.domain.repository.NewsRepository
import com.iyanuoluwa.inews.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getTopHeadlines(category: Category) = flow {
        emit(Resource.Loading)
        try {
            val response = newsApi.getTopHeadlines(category.categoryName)
            Log.i("NewsRepository", response.toString())
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    override suspend fun getOtherNews() = flow {
        emit(Resource.Loading)
        try {
            val response = newsApi.getOtherNews()
            Log.i("NewsRepository1", response.toString())
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}