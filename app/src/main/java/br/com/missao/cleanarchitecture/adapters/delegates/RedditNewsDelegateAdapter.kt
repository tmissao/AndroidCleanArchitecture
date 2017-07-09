package br.com.missao.cleanarchitecture.adapters.delegates

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.missao.cleanarchitecture.R
import br.com.missao.cleanarchitecture.extensions.inflate
import br.com.missao.cleanarchitecture.interfaces.ViewType
import br.com.missao.cleanarchitecture.interfaces.ViewTypeDelegateAdapter
import br.com.missao.cleanarchitecture.pojos.wrappers.RedditNewsWrapper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Delegate Reddit News Adapter for [RedditNewsWrapper]
 */
class RedditNewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsWrapper)
    }

    /**
     * Class to implement [RecyclerView.ViewHolder]
     */
    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_news)
    ) {
        fun bind(item: RedditNewsWrapper) = with(itemView) {
            Glide.with(this.context).load(item.thumbnail).into(this.imgThumbnail)
            this.textDescription.text = item.title
            this.textAuthor.text = item.author
            this.textComments.text = context.resources.getQuantityString(R.plurals.comments, item.numComments, item.numComments)
            this.setOnClickListener {
                val browserIntent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(item.url))
                this.context.startActivity(browserIntent)
            }
        }
    }
}