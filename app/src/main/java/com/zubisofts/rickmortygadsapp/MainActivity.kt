package com.zubisofts.rickmortygadsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zubisofts.rickmortygadsapp.adapter.CharacterAdapter
import com.zubisofts.rickmortygadsapp.adapter.CharacterLoadingAdapter
import com.zubisofts.rickmortygadsapp.databinding.ActivityMainBinding
import com.zubisofts.rickmortygadsapp.network.createApiService
import com.zubisofts.rickmortygadsapp.repo.CharacterRepositoryImpl
import com.zubisofts.rickmortygadsapp.viewmodel.CharacterViewModel
import com.zubisofts.rickmortygadsapp.viewmodel.CharacterViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private val characterAdapter = CharacterAdapter()
    private val apiService = createApiService()
    private val characterViewModel: CharacterViewModel by viewModels{
        CharacterViewModelFactory(
            characterRepository = CharacterRepositoryImpl(
                apiService = apiService
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterViewModel.getCharacters()
        setupRecyclerView()
        observeViewModelCharacters()
    }

    private fun observeViewModelCharacters() {
        lifecycleScope.launch {
            characterViewModel.characterData
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect() {data->
                    if(data != null){
                        characterAdapter.submitData(data)
                    }
                }
        }
    }

    private fun setupRecyclerView() {
        binding.characterList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadingAdapter(
                    retry = { characterAdapter.retry() }
                ),
                footer = CharacterLoadingAdapter(
                    retry = { characterAdapter.retry() }
                )
            )
        }
    }
}