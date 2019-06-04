package com.edicasoft.journeyreport.works.di;

import androidx.work.Worker;
import dagger.android.AndroidInjector;

public interface HasWorkerInjector {

    AndroidInjector<Worker> workerInjector();
}
