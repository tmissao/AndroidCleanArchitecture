package br.com.missao.cleanarchitecture.presenters

import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredViewOperations
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test for class [RedditPresenter]
 */
class RedditPresenterTest {

    val view: MainMvpRequiredViewOperations = mock()
    val domain: MainMvpModelOperations = mock()
    lateinit var presenter: RedditPresenter

    @Before
    fun setUp() {
        presenter = RedditPresenter(domain)
        presenter.view = view
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getInitialNewsDefault() {
        val expected = 0
        presenter.getInitialNews()

        verify(domain).getInitialNews(expected)
    }

    @Test
    fun getInitialNews() {
        val expected = 10
        presenter.getInitialNews(expected)

        verify(domain).getInitialNews(expected)
    }

    @Test
    fun onGetInitialNews() {
        val news = listOf<RedditNewsDataResponse>(mock())
        presenter.onGetInitialNews(news)

        verify(view).onGetInitialNews(news)
    }

    @Test
    fun onNetworkError() {
        presenter.onNetworkError()

        verify(view).onNetworkError()
    }

}