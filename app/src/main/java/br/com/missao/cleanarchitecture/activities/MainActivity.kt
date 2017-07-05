package br.com.missao.cleanarchitecture.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.missao.cleanarchitecture.R
import br.com.missao.cleanarchitecture.injections.components.DaggerViewComponent
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredViewOperations
import br.com.missao.cleanarchitecture.pojos.dtos.RedditNewsDataResponse
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMvpRequiredViewOperations {

    @Inject lateinit var presenter: MainMvpPresenterOperations
    @Inject lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
    }

    fun inject() {
        DaggerViewComponent.builder().build().inject(this)
        presenter.setView(this)
    }

    override fun onStart() {
        super.onStart()
        getInitialNews(0)
    }

    /**
     * Obtains reddit most relevant news, ignoring the firsts [offset]
     */
    fun getInitialNews(offset: Int) {
        presenter.getInitialNews(offset)
    }

    override fun onGetInitialNews(news: List<RedditNewsDataResponse>) {
        logger.d("MainActivity", "OnGetInitialNews")

    }

    override fun onNetworkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
