package br.com.missao.cleanarchitecture.activities

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.missao.cleanarchitecture.R
import br.com.missao.cleanarchitecture.adapters.NewsAdapter
import br.com.missao.cleanarchitecture.extensions.app
import br.com.missao.cleanarchitecture.extensions.toast
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpRequiredViewOperations
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper
import br.com.missao.cleanarchitecture.utils.InfiniteScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_empty.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMvpRequiredViewOperations {

    /**
     * Presenter
     */
    @Inject lateinit var presenter: MainMvpPresenterOperations

    /**
     * Logger to report errors
     */
    @Inject lateinit var logger: Logger

    /**
     * Threshold for request new items for recyclerView
     */
    private val scrollThreshold = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        setupViews()
        setupEvents()
    }

    /**
     * Injects activity dependencies
     */
    fun inject() {
        this.app.getDaggerViewComponent().inject(this)
        presenter.setView(this)
    }

    /**
     * Initiates activity views
     */
    fun setupViews() {
        recyclerNews.apply {
            val linearLayout = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            layoutManager = linearLayout
            adapter = NewsAdapter()
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ getInitialNews() }, scrollThreshold, linearLayout))
        }

        imageDefault.setColorFilter(ContextCompat.getColor(this, R.color.disableTextColor),
                PorterDuff.Mode.SRC_IN)
    }

    /**
     * Defines views events
     */
    fun setupEvents() {
        llContainerEmpty.setOnClickListener { getInitialNews() }
    }

    override fun onStart() {
        super.onStart()
        getInitialNews()
    }

    /**
     * Adds [news] to recycler view adapter
     */
    fun addRedditNewsToAdapter(news: List<RedditNewsWrapper>) {
        val newsAdapter = recyclerNews.adapter as? NewsAdapter
        newsAdapter?.addNews(news)
    }

    /**
     * Obtains number of elements in adapter
     */
    private fun getNumberOfItemsInAdapter(): Int {
        val newsAdapter = recyclerNews.adapter as? NewsAdapter
        return newsAdapter?.getItemsSize() ?: 0
    }

    /**
     * Shows Internet Error View if the recycler has none items, otherwise shows a toast message
     */
    private fun displayInternetError() {
        when (getNumberOfItemsInAdapter()) {
            0 -> {
                recyclerNews.visibility = View.GONE
                llContainerEmpty.visibility = View.VISIBLE
            }

            else -> {
                recyclerNews.visibility = View.VISIBLE
                llContainerEmpty.visibility = View.GONE
                toast(getString(R.string.no_internet_connection))
            }
        }
    }

    /**
     * Obtains reddit most relevant news
     */
    private fun getInitialNews() {
        presenter.getInitialNews(getNumberOfItemsInAdapter())
    }

    override fun onGetInitialNews(news: List<RedditNewsWrapper>) {
        addRedditNewsToAdapter(news)
    }

    override fun onNetworkError() {
        displayInternetError()
    }
}
