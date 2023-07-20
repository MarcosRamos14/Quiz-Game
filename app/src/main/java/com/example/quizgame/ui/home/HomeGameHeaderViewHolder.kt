package com.example.quizgame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quizgame.databinding.ItemGameHeaderBinding

class HomeGameHeaderViewHolder(
 itemBinding: ItemGameHeaderBinding
) : BaseHomeViewHolder(itemBinding.root) {

    override fun bind(item: HomeAdapterItem) {
        (item as? HomeAdapterItem.GameHeader)?.let {
            it.playerNameTitle
        }
    }

    companion object {
        fun create(parent: ViewGroup): HomeGameHeaderViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemGameHeaderBinding.inflate(inflate, parent, false)
            return HomeGameHeaderViewHolder(itemBinding)
        }
    }
}