package com.workmanagerwithroomdb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.example.mvvmlogindemo.viewModel.MainActivityViewModel
import com.example.mvvmlogindemo.viewModelFactory.MainViewModelFactory
import com.workmanagerwithroomdb.R
import com.workmanagerwithroomdb.adapter.QuoteDataAdapter
import com.workmanagerwithroomdb.databinding.ActivityMainBinding
import com.workmanagerwithroomdb.service.WorkerSyncData
import com.workmanagerwithroomdb.network.ApiService
import com.workmanagerwithroomdb.network.NetworkResult
import com.workmanagerwithroomdb.network.RetrofitHelper
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: QuoteDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this,MainViewModelFactory(QuoteRepositry( RetrofitHelper.getClient().create(ApiService::class.java)))).get(MainActivityViewModel::class.java)
        viewModel.quoteResponse.observe(this, Observer {
            when(it){
                is NetworkResult.Loading->{
                    Log.e("Inside","Home Loading");
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Sucess->{
                    Log.e("Inside","Home Success"+it.data?.results);
                    adapter = QuoteDataAdapter(it.data?.results!!)
                    binding.rvNotes.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkResult.Error->{
                    Log.e("Inside","Home Error"+it.errorMessage.toString());
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }


    private fun startBackgroundTask(){
        try{
            //1) set the constraint for your workrequest
            val constraintBuilder = Constraints.Builder()
            constraintBuilder.setRequiredNetworkType(NetworkType.CONNECTED)
            constraintBuilder.setRequiresBatteryNotLow(true)
            constraintBuilder.setRequiresCharging(true)
            constraintBuilder.setRequiresStorageNotLow(true)
            //constraintBuilder.setRequiresDeviceIdle(true)
            //2) create Work Request It may be OneTime or Perodic dependind on your need
            val oneTimeWorkRequestBuilder = OneTimeWorkRequestBuilder<WorkerSyncData>()
            //oneTimeWorkRequestBuilder.setInputMerger()
            //oneTimeWorkRequestBuilder.setInputData()
            //oneTimeWorkRequestBuilder.addTag("")
            //oneTimeWorkRequestBuilder.setConstraints(constraintBuilder.build())

            val perodicWorkRequestBuilder = PeriodicWorkRequestBuilder<WorkerSyncData>(10,TimeUnit.MINUTES)
            //perodicWorkRequestBuilder.setInputData()
            //perodicWorkRequestBuilder.setConstraints(constraintBuilder.build())
            //perodicWorkRequestBuilder.addTag("")


            //3) start the worker or schedule work using workmanager
            WorkManager.getInstance(applicationContext).enqueue(oneTimeWorkRequestBuilder.build())
            WorkManager.getInstance(applicationContext).enqueue(perodicWorkRequestBuilder.build())

            //4) stop worker
            WorkManager.getInstance(applicationContext).cancelAllWork()
            WorkManager.getInstance(applicationContext).cancelWorkById(oneTimeWorkRequestBuilder.build().id)

            //5) work chaining

        }catch (ex:Exception){
            ex.printStackTrace()
        }

    }

}