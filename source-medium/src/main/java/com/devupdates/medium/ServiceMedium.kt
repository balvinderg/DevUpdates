package com.devupdates.medium

import com.devupdates.medium.models.MediumResponse
import retrofit2.Response
import retrofit2.http.*

interface ServiceMedium {

    @GET("https://run.mocky.io/v3/e4d0b8ec-a106-4d02-a037-d77445afaa03")
    @Headers("accept: application/json", "x-xsrf-token: 1")
    suspend fun getFeed(
        @Query(USERNAME) username: String,
//        @Path(USERNAME) username: String,
        @QueryMap request: MutableMap<String, String?>
    ): Response<MediumResponse>


    @GET("{$USERNAME}/load-more")
    @Headers("accept: application/json", "x-xsrf-token: 1")
    suspend fun getTaggedFeed(
        @Path(USERNAME) username: String,
        @QueryMap request: MutableMap<String, String?>
    ): Response<MediumResponse>

    companion object {
        private const val USERNAME = "username"
        const val ENDPOINT = "https://medium.com/"
        const val SERVICE_KEY = "MEDIUM"
    }
}