package com.iyanuoluwa.inews.domain.usecase

import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.domain.repository.NewsRepository
import javax.inject.Inject

class HomeFragmentUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun getTopHeadlines(category: Category) = newsRepository.getTopHeadlines(category)

    suspend fun getOtherNews() = newsRepository.getOtherNews()
}