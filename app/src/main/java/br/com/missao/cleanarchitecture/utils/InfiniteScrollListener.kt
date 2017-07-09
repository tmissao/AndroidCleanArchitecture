package br.com.missao.cleanarchitecture.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Listener to perform infinite scroll
 */
class InfiniteScrollListener(

        /**
         * Function to be called when the adapter is near to end
         */
        private val func: () -> Unit,

        /**
         * Threshold to call the [func]
         */
        private val visibleThreshold: Int = 5,

        /**
         * Layout that controls the adapter
         */
        private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    /**
     * Previous amount of items in adapter
     */
    private var previousTotal = 0

    /**
     * Indicates if the [func] was already called
     */
    private var loading = true


    /**
     * Position of the first visible item
     */
    private var firstVisibleItem = 0

    /**
     * Total number of visible items on adapter
     */
    private var visibleItemCount = 0

    /**
     * Total number of items in adapter
     */
    private var totalItemCount = 0

    /**
     * Once a positive scroll [dy] happened it is calculate if the total items on the adapter minus
     * the visible items on it is less or equal than firstVisible item plus a threshold. If the
     * equation is true a function [func] is called meaning the ending is near
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                func()
                loading = true
            }

        }
    }
}