package br.com.missao.cleanarchitecture.domains

import br.com.missao.cleanarchitecture.BuildConfig
import br.com.missao.cleanarchitecture.RxTest
import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredPresenterOperations
import br.com.missao.cleanarchitecture.pojos.dtos.RedditChildrenResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditDataResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsResponse
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.rxkotlin.toSingle
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.ConnectException

/**
 * Tests for class [RedditDomain]
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = App::class)
class RedditDomainTest : RxTest() {

    var api: RedditAPI = mock()
    var logger: Logger = mock()
    var presenter: MainMvpRequiredPresenterOperations = mock()
    var domain: RedditDomain = RedditDomain(api, logger)

    @Before
    fun setUp() {
        domain.presenter = presenter
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getInitialNews() {
        val offset = 10
        val d1: RedditNewsDataResponse = mock()
        val d2: RedditNewsDataResponse = mock()

        val rc1 = RedditChildrenResponse(d1)
        val rc2 = RedditChildrenResponse(d2)

        val rd = RedditDataResponse(listOf(rc1, rc2))

        val response = RedditNewsResponse(rd)

        whenever(api.getTopNews(offset, domain.limit))
                .thenReturn(response.toSingle().toObservable())

        domain.getInitialNews(offset)

        argumentCaptor<List<RedditNewsDataResponse>>().apply {
            verify(presenter).onGetInitialNews(capture())
            Assertions.assertThat(firstValue).containsExactly(d1, d2)
        }
    }

    @Test
    fun getInitialNewsConnectionException() {
        val offset = 10

        whenever(api.getTopNews(offset, domain.limit))
                .thenReturn(Observable.error { ConnectException() })

        domain.getInitialNews(offset)

        verify(presenter).onNetworkError()
    }

    @Test
    fun getInitialException() {
        val offset = 10
        val expection = Exception()

        whenever(api.getTopNews(offset, domain.limit))
                .thenReturn(Observable.error { expection })

        domain.getInitialNews(offset)

        verify(logger).e(domain.TAG, expection)
    }
}