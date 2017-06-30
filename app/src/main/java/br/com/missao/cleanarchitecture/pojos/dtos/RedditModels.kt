package br.com.missao.cleanarchitecture.pojos.dtos

/**
 * RedditApi's Models
 */

/**
 * API first level response
 */
data class RedditNewsResponse(val data: RedditDataResponse)

/**
 * API secontd level response
 */
data class RedditDataResponse(val children: List<RedditChildrenResponse>)

/**
 * API third level response
 */
data class RedditChildrenResponse(val data: RedditNewsDataResponse)

/**
 * News information
 */
data class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val thumbnail: String,
        val url: String
)