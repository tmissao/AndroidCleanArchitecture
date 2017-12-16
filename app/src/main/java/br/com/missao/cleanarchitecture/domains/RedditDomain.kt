package br.com.missao.cleanarchitecture.domains

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.bases.DomainBase
import br.com.missao.cleanarchitecture.exceptions.NoConnectivityException
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredPresenterOperations
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Resolves inquiries relate to Reddit API
 */
class RedditDomain(private val api: RedditAPI, private val logger: Logger,
                   private val mapper: RedditNewsMapper) :
        DomainBase<MainMvpRequiredPresenterOperations>(),
        MainMvpModelOperations {

    /**
     * Class's TAG
     */
    val TAG = RedditDomain::class.java.simpleName

    /**
     * Max number of News that should be got from [RedditAPI]
     */
    val limit = 20

    override fun getInitialNews(offset: Int) {

        Observable.defer { api.getTopNews(offset, limit) }
                .subscribeOn(Schedulers.io())
                .map { it.data.children.map { it.data } }
                .concatMapIterable { it -> it }
                .map { it -> mapper.toWrapper(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { presenter?.onGetInitialNews(it) },
                        {
                            when (it) {
                                is NoConnectivityException -> presenter?.onNetworkError()
                                else -> logger.e(TAG, it)
                            }
                        }
                )
    }

    override fun setPresenter(presenter: MainMvpRequiredPresenterOperations) {
        super.presenter = presenter
    }
}