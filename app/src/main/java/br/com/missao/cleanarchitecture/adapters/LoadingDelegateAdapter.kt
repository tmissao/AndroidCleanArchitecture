package br.com.missao.cleanarchitecture.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.missao.cleanarchitecture.R
import br.com.missao.cleanarchitecture.extensions.inflate
import br.com.missao.cleanarchitecture.interfaces.ViewType
import br.com.missao.cleanarchitecture.interfaces.ViewTypeDelegateAdapter

/**
 * Loading Delegate Adapter
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    /**
     * Class to implement [RecyclerView.ViewHolder]
     */
    private class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_loading))
}