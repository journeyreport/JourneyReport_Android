package com.edicasoft.journeyreport.works

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object SyncWorkBuilder {

    private val TAG = "SyncWorker"

    fun start() {
        val constraints = Constraints.Builder().setRequiredNetworkType(
            NetworkType.CONNECTED).build()
        val uploadWorkRequest = PeriodicWorkRequest.Builder(
            SyncWorker::class.java,
            1,
            TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag(TAG)
            .build()
        WorkManager.getInstance()
            .enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.KEEP, uploadWorkRequest)
    }
}
