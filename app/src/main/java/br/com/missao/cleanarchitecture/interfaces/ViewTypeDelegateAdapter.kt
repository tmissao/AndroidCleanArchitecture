package br.com.missao.cleanarchitecture.interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Defines essentials methods to work with [RecyclerView]
 */
interface ViewTypeDelegateAdapter {

    /**
     * Creates a [RecyclerView.ViewHolder] for the [RecyclerView]
     */
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Binds [RecyclerView.ViewHolder] data and events
     */
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}