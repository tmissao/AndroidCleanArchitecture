package br.com.missao.cleanarchitecture.mvp

import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper

/**
 * Application's Main MVP
 */

/**
 * Required View Operations
 */
interface MainMvpRequiredViewOperations {

    /**
     * Receives Reddit News
     * @param news list of [RedditNewsWrapper]
     */
    fun onGetInitialNews(news: List<RedditNewsWrapper>)

    /**
     * Handles Network Error
     */
    fun onNetworkError()
}

/**
 * Operations provided by Presenter
 */
interface MainMvpPresenterOperations {

    /**
     * Obtains the most relevant Reddit News
     * @param offset Number of first ignored news, the default value is zero
     */
    fun getInitialNews(offset: Int = 0)

    /**
     * Defines view reference
     */
    fun setView(view: MainMvpRequiredViewOperations)

}

/**
 * Required Presenter Operations
 */
interface MainMvpRequiredPresenterOperations {

    /**
     * Receives Reddit News
     * @param news list of [RedditNewsWrapper]
     */
    fun onGetInitialNews(news: List<RedditNewsWrapper>)

    /**
     * Handles Network Error
     */
    fun onNetworkError()

}

/**
 * Operations provided by Model
 */
interface MainMvpModelOperations {

    /**
     * Obtains the most relevant Reddit News
     * @param offset Number of first ignored news
     */
    fun getInitialNews(offset: Int)

    /**
     * Defines presenter reference
     */
    fun setPresenter(presenter: MainMvpRequiredPresenterOperations)
}

