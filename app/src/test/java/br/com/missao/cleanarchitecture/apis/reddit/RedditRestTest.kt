package br.com.missao.cleanarchitecture.apis.reddit

import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsResponse
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Testes para a classe [RedditRest]
 */
class RedditRestTest {

    lateinit var api: RedditAPI
    lateinit var rest: RedditRest
    lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        api = mock()
        retrofit = mock()
        whenever(retrofit.create(RedditAPI::class.java)).doReturn(api)

        rest = RedditRest(retrofit)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getTopNews() {
        val limit = 10
        val after = 5
        val response: RedditNewsResponse = mock()
        whenever(api.getTopNews(after, limit)).doReturn(Observable.just(response))

        val result = rest.getTopNews(after, limit).blockingFirst()
        Assertions.assertThat(result).isEqualTo(response)
    }

}