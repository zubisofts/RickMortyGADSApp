package com.zubisofts.rickmortygadsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubisofts.rickmortygadsapp.data.model.Character
import com.zubisofts.rickmortygadsapp.databinding.ItemCharacterBinding
import com.zubisofts.rickmortygadsapp.util.CharacterDiffUtil

class CharacterAdapter : PagingDataAdapter<Character, CharacterViewHolder>(CharacterDiffUtil()) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character: Character? = getItem(position)
        if(character != null) {
            holder.bind(character)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

}

class  CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.tvName.text = character.name
        binding.tvOrigin.text = character.origin.name
    }
}