package br.com.missao.cleanarchitecture.constants

import br.com.missao.cleanarchitecture.adapters.delegates.LoadingDelegateAdapter
import br.com.missao.cleanarchitecture.adapters.delegates.RedditNewsDelegateAdapter

/**
 * Adapter View Holder codes
 */
object AdapterConstants {

    /**
     *  Identifier to [RedditNewsDelegateAdapter]
     */
    val NEWS = 1
    
    /**
     *  Identifier to [LoadingDelegateAdapter]
     */
    val LOADING = 2
}