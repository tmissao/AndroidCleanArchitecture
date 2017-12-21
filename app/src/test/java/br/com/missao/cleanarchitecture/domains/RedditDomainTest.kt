package br.com.missao.cleanarchitecture.domains

import br.com.missao.cleanarchitecture.BuildConfig
import br.com.missao.cleanarchitecture.RxTest
import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.database.daos.RedditNewsDao
import br.com.missao.cleanarchitecture.exceptions.NoConnectivityException
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredPresenterOperations
import br.com.missao.cleanarchitecture.pojos.dtos.RedditChildrenResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditDataResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsResponse
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper
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

/**
 * Tests for class [RedditDomain]
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class RedditDomainTest : RxTest() {

    val api: RedditAPI = mock()
    val logger: Logger = mock()
    val presenter: MainMvpRequiredPresenterOperations = mock()
    val mapper: RedditNewsMapper = mock()
    val dao: RedditNewsDao = mock()
    var domain: RedditDomain = RedditDomain(api, logger, mapper, dao)

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

        val w1: RedditNewsWrapper = mock()
        val w2: RedditNewsWrapper = mock()

        whenever(api.getTopNews(offset, domain.limit))
                .thenReturn(response.toSingle().toObservable())

        whenever(mapper.toWrapper(d1)).thenReturn(w1)
        whenever(mapper.toWrapper(d2)).thenReturn(w2)

        domain.getInitialNews(offset)

        argumentCaptor<List<RedditNewsWrapper>>().apply {
            verify(presenter).onGetInitialNews(capture())
            Assertions.assertThat(firstValue).containsExactly(w1, w2)
        }
    }

    @Test
    fun getInitialNewsConnectionException() {
        val offset = 10

        whenever(api.getTopNews(offset, domain.limit))
                .thenReturn(Observable.error { NoConnectivityException() })

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