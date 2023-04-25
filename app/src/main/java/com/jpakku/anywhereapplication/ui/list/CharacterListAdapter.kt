package com.jpakku.anywhereapplication.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jpakku.anywhereapplication.data.CharacterItem
import com.jpakku.anywhereapplication.databinding.ListItemBinding

class CharacterListAdapter(private val itemClick: (CharacterItem) -> Unit) :
    ListAdapter<CharacterItem, RecyclerView.ViewHolder>(CharacterDifferCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = ListItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).run {
        this.onClick = itemClick
        root.tag = this
        object : RecyclerView.ViewHolder(root) {}
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int
    ) = getItem(position).let {
        (holder.itemView.tag as ListItemBinding).apply {
            character = it
        }.executePendingBindings()
    }

    companion object CharacterDifferCallback : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem == newItem
        }

    }
}