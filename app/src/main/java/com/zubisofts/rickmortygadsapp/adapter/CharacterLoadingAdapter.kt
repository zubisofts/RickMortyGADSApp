package com.zubisofts.rickmortygadsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubisofts.rickmortygadsapp.databinding.LoadingStateItemBinding

class CharacterLoadingAdapter(private val retry: ()->Unit) :
    LoadStateAdapter<CharacterLoadingAdapter.LoadingStateViewHolder>(){

        class  LoadingStateViewHolder(private val binding: LoadingStateItemBinding, retry:()->Unit):
                RecyclerView.ViewHolder(binding.root){
            init {
                binding.btnRetry.setOnClickListener { retry.invoke() }
            }

            fun bindState(loadState: LoadState){
                if(loadState is LoadState.Error) {
                    binding.tvErrorMessage.text= loadState.error.localizedMessage
                }
                binding.progressBar.isVisible = loadState is LoadState.Loading
                binding.btnRetry.isVisible = loadState is LoadState.Error
                binding.tvErrorMessage.isVisible = loadState !is LoadState.Loading
            }

        }


    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
       val binding = LoadingStateItemBinding.inflate(LayoutInflater.from(parent.context))
        return LoadingStateViewHolder(binding, retry)
    }
}