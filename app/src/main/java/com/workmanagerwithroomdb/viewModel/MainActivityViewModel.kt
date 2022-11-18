package com.example.mvvmlogindemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.workmanagerwithroomdb.modal.quoteResponse.QuoteResponse
import com.workmanagerwithroomdb.network.NetworkResult

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: QuoteRepositry): ViewModel(){
    val quoteResponse: LiveData<NetworkResult<QuoteResponse>>
    get() = repository.quoteResponse
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQoutes(1)
        }
    }

}