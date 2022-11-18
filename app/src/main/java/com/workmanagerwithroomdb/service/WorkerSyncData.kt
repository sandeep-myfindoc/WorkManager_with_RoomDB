package com.workmanagerwithroomdb.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class WorkerSyncData(context: Context, workerParams: WorkerParameters): Worker(context, workerParams)
{
    override fun doWork(): Result {
        try {
            // Do code here to perform Long Running Operations
        }catch (ex:Exception){
            ex.printStackTrace()
        }
        return Result.retry()
    }

    override fun onStopped() {
        super.onStopped()
    }
}
