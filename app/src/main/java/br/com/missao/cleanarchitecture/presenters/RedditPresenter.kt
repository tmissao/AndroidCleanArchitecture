package br.com.missao.cleanarchitecture.presenters

import br.com.missao.cleanarchitecture.bases.PresenterBase
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredPresenterOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredViewOperations
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper

/**
 * Reddit's Presenter
 */
class RedditPresenter(domain: MainMvpModelOperations)
    : PresenterBase<MainMvpRequiredViewOperations, MainMvpModelOperations>(),
        MainMvpPresenterOperations, MainMvpRequiredPresenterOperations {

    init {
        super.domain = domain
        super.domain?.setPresenter(this)
    }

    override fun getInitialNews(offset: Int) {
        domain?.getInitialNews(offset)
    }

    override fun setView(view: MainMvpRequiredViewOperations) {
        super.view = view
    }

    override fun onGetInitialNews(news: List<RedditNewsWrapper>) {
        view?.onGetInitialNews(news)
    }

    override fun onNetworkError() {
        view?.onNetworkError()
    }

}