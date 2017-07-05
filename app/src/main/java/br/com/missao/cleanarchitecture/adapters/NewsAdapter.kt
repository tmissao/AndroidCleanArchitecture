package br.com.missao.cleanarchitecture.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.missao.cleanarchitecture.constants.AdapterConstants
import br.com.missao.cleanarchitecture.interfaces.ViewType
import br.com.missao.cleanarchitecture.interfaces.ViewTypeDelegateAdapter
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper
import java.util.*

/**
 * Created by Tiago on 03/07/17.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, RedditNewsDelegateAdapter())
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return items.get(position).getViewType()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Adds the items in the list [news] in the adapter
     */
    fun addNews(news: List<RedditNewsWrapper>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 )
    }
}