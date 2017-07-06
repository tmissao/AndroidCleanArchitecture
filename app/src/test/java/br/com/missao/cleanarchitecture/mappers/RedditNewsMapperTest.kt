package br.com.missao.cleanarchitecture.mappers

import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Tests for class [RedditNewsMapper]
 */
class RedditNewsMapperTest {

    val mapper = RedditNewsMapper()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun toWrapper() {
        val author = "Tiago Missão"
        val title = "Kotlin Rules"
        val numComments = 12345
        val thumbnail = "http://myimage.com"
        val url = "http://github/tmissao"

        val dto = RedditNewsDataResponse(author, title, numComments, thumbnail, url)
        val result = mapper.toWrapper(dto)

        Assertions.assertThat(result.author).isEqualTo(author)
        Assertions.assertThat(result.title).isEqualTo(title)
        Assertions.assertThat(result.numComments).isEqualTo(numComments)
        Assertions.assertThat(result.thumbnail).isEqualTo(thumbnail)
        Assertions.assertThat(result.url).isEqualTo(url)
    }

}