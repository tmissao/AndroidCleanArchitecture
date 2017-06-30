package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.app.App
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.robolectric.RuntimeEnvironment

/**
 * Tests for class [AppModule]
 */
class AppModuleTest {

    lateinit var module: AppModule

    @Before
    fun setUp() {
        val app: App = mock()
        module = AppModule(app)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getApp() {
        val result = module.providesApp()
        Assertions.assertThat(result).isEqualTo(RuntimeEnvironment.application)
    }

}