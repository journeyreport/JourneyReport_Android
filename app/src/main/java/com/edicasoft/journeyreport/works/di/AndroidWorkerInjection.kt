package com.edicasoft.journeyreport.works.di

import android.app.Application
import androidx.core.util.Preconditions.checkNotNull
import androidx.work.Worker

object AndroidWorkerInjection {

    fun inject(worker: Worker) {
        val application = worker.applicationContext as Application
        if (application is HasWorkerInjector) {
            val workerInjector = (application as HasWorkerInjector).workerInjector()
            checkNotNull(workerInjector)
            workerInjector.inject(worker)
        } else {
            throw IllegalArgumentException(
                "${application.javaClass.canonicalName} does not implement" +
                    " ${HasWorkerInjector::class.java.canonicalName}")
        }
    }
}
