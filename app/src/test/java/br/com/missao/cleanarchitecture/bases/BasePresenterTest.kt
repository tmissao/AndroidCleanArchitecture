package br.com.missao.cleanarchitecture.bases

import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Tests for class [BasePresenter]
 */
class BasePresenterTest {

    lateinit var presenter: BasePresenter<Int, String>

    @Before
    fun setUp() {
        presenter = BasePresenter()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getView() {
        val expected = 10
        var result: Int? = null

        result = presenter.view
        Assertions.assertThat(result).isNull()

        presenter.view = expected
        result = presenter.view
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setView() {
        val expected = 10
        var result: Int? = null

        presenter.view = expected
        result = presenter.view
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun getDomain() {
        val expected = "Hello"
        var result: String? = null

        result = presenter.domain
        Assertions.assertThat(result).isNull()

        presenter.domain = expected
        result = presenter.domain
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setDomain() {
        val expected = "Hello"
        var result: String? = null

        presenter.domain = expected
        result = presenter.domain
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun clear() {
        var resultView: Int? = null
        var resultDomain: String? = null
        presenter.domain = "hello"
        presenter.view = 1
        presenter.clear()

        resultView = presenter.view
        resultDomain = presenter.domain

        Assertions.assertThat(resultDomain).isNull()
        Assertions.assertThat(resultView).isNull()

    }

}