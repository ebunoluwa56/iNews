package com.iyanuoluwa.inews.domain.usecase

import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.domain.repository.NewsRepository
import javax.inject.Inject

class OtherFragmentsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun getOtherSegments(category: Category) = newsRepository.getOtherSegments(category)
}