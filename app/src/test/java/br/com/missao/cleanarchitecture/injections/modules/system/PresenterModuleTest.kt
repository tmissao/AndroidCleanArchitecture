package br.com.missao.cleanarchitecture.injections.modules.system

import br.com.missao.cleanarchitecture.injections.modules.PresenterModule
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Tests for class [PresenterModule]
 */
class PresenterModuleTest {

    val domain: MainMvpModelOperations = mock()
    val module = PresenterModule()

    @Test
    fun providesMainPresenter() {
        val expected = module.providesMainPresenter(domain)
        Assertions.assertThat(expected).isInstanceOf(MainMvpPresenterOperations::class.java)
    }

}