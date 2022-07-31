package com.zubisofts.rickmortygadsapp.util

import androidx.recyclerview.widget.DiffUtil
import com.zubisofts.rickmortygadsapp.data.model.Character

class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }
}