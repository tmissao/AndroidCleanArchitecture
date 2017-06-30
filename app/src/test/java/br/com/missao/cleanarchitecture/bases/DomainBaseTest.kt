package br.com.missao.cleanarchitecture.bases

import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Tests for class [DomainBase]
 */
class DomainBaseTest {

    lateinit var domain: DomainBase<String>

    @Before
    fun setUp() {
        domain = DomainBase()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPresenter() {
        val expected = "Hello"
        var result: String? = null

        result = domain.presenter
        Assertions.assertThat(result).isNull()

        domain.presenter = expected
        result = domain.presenter
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setPresenter() {
        val expected = "Hello"
        var result: String? = null

        domain.presenter = expected
        result = domain.presenter
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun clear() {
        val expected = "Hello"
        var result: String? = null

        domain.presenter = expected
        domain.clear()
        result = domain.presenter
        Assertions.assertThat(result).isNull()
    }

}