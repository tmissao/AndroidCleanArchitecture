package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Tests for class [MapperModule]
 */
class MapperModuleTest {

    val module = MapperModule()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun providesRedditNewsMapper() {
        val result = module.providesRedditNewsMapper()
        Assertions.assertThat(result).isInstanceOf(RedditNewsMapper::class.java)
    }

}