package br.com.missao.cleanarchitecture.injections.modules.system

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.database.daos.RedditNewsDao
import br.com.missao.cleanarchitecture.injections.modules.DomainModule
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Testes for class [DomainModule]
 */
class DomainModuleTest {

    val api: RedditAPI = mock()
    val logger: Logger = mock()
    val mapper: RedditNewsMapper = mock()
    val module: DomainModule = DomainModule()
    val dao: RedditNewsDao = mock()

    @Test
    fun provides() {
        val result = module.providesMainDomain(api, logger, mapper, dao)
        Assertions.assertThat(result).isInstanceOf(MainMvpModelOperations::class.java)
    }

}