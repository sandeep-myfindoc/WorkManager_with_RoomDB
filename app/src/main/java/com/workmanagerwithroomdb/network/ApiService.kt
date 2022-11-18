package com.workmanagerwithroomdb.network
import com.workmanagerwithroomdb.modal.quoteResponse.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// define the service here that you want to call
interface ApiService {
    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int):Response<QuoteResponse>
}
/*
* There are three way to pass Header
* 1) Using Header annotaion along @GET or @POSt Annotation. we use it to pass static values in header
* 2) Pass Header inside the function call
* 3) to pass dynamic header we use intercept
* */