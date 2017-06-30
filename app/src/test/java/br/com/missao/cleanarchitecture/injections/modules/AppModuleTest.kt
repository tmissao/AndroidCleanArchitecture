package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.BuildConfig
import br.com.missao.cleanarchitecture.app.App
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Tests for class [AppModule]
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = App::class)
class AppModuleTest {

    lateinit var module: AppModule


    @Before
    fun setUp() {
        module = AppModule(RuntimeEnvironment.application as App)
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