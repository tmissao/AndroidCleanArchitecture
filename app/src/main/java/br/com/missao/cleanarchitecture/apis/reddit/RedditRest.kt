package br.com.missao.cleanarchitecture.apis.reddit

import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * Reddit Rest API
 */
class RedditRest(retrofit: Retrofit) : RedditAPI {

    /**
     * API to perform request
     */
    private val api: RedditAPI = retrofit.create(RedditAPI::class.java)

    override fun getTopNews(after: Int, limit: Int): Observable<RedditNewsResponse> {
        return api.getTopNews(after, limit)
    }
}