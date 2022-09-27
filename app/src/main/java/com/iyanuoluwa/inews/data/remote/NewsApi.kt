package com.iyanuoluwa.inews.data.remote

import com.iyanuoluwa.inews.data.model.NewsResponse
import com.iyanuoluwa.inews.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/{category}?country=us&apiKey=${API_KEY}")
    suspend fun getTopHeadlines(
        @Path("category") string: String,
    ): NewsResponse

    @GET("v2/top-headlines?country=de&apiKey=${API_KEY}")
    suspend fun getOtherNews(): NewsResponse

    @GET("v2/top-headlines?country=us&apiKey=5b6f1f291c234279833002d66e61f3e5")
    suspend fun getOtherSegments(
        @Query("category") category : String
    ) : NewsResponse
}