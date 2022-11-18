package com.example.mvvmlogindemo.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.workmanagerwithroomdb.modal.quoteResponse.QuoteResponse
import com.workmanagerwithroomdb.network.ApiService
import com.workmanagerwithroomdb.network.NetworkResult
import org.json.JSONObject
import retrofit2.Response

class QuoteRepositry(private val quoteService: ApiService) {
    private var _quoteResponse =  MutableLiveData<NetworkResult<QuoteResponse>>()
    val quoteResponse: LiveData<NetworkResult<QuoteResponse>>
        get() = _quoteResponse

    /*suspend fun getQoutes(page: Int): QuoteList?{
        val result = quoteService.getQuotes(page)
        return result.body()
    }*/

    suspend fun getQoutes(page: Int){
        val result = quoteService.getQuotes(page)
        handleResponse(result)
    }
    private fun handleResponse(response: Response<QuoteResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _quoteResponse.postValue(NetworkResult.Sucess(response.body()))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _quoteResponse.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _quoteResponse.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}