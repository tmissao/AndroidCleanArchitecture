package br.com.missao.cleanarchitecture.mappers

import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper

/**
 * Parse methods for class [RedditNewsWrapper]
 */
class RedditNewsMapper {

    /**
     * Parses [dto] to [RedditNewsWrapper]
     */
    fun toWrapper(dto: RedditNewsDataResponse): RedditNewsWrapper
            = RedditNewsWrapper(dto.author, dto.title, dto.num_comments, dto.thumbnail, dto.url)

}
