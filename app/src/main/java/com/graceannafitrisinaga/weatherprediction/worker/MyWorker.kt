package com.graceannafitrisinaga.weatherprediction.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("Background Task", "Doing Work")

        return Result.success()
    }
}