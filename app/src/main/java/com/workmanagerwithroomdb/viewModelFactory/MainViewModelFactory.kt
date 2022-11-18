package com.example.mvvmlogindemo.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.example.mvvmlogindemo.viewModel.MainActivityViewModel
import com.workmanagerwithroomdb.network.ApiService

class MainViewModelFactory(private val quoteRepository: QuoteRepositry):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(quoteRepository) as T
    }
}