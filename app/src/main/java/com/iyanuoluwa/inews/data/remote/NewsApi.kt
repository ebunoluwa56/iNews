package com.iyanuoluwa.inews.data.remote

import com.iyanuoluwa.inews.data.model.NewsResponse
import com.iyanuoluwa.inews.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("v2/{category}?country=us&apiKey=${API_KEY}")
    suspend fun getTopHeadlines(
        @Path("category") string: String,
    ): NewsResponse

    @GET("v2/top-headlines?country=de&apiKey=${API_KEY}")
    suspend fun getOtherNews(): NewsResponse
}