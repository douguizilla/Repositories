package com.odougle.repositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.odougle.repositories.R
import com.odougle.repositories.databinding.ActivityMainBinding
import com.odougle.repositories.presentation.MainViewModel
import com.odougle.repositories.ui.adapter.RepoListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.rvRepos.adapter = adapter

        viewModel.getRepoList("douguizilla")

        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> {
                }
                is MainViewModel.State.Error -> {
                }
                is MainViewModel.State.Sucess -> {
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e(TAG, "onQueryTextSudmit: $query")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextSudmit: $newText")
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }
}