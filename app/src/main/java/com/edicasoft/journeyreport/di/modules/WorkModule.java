package com.edicasoft.journeyreport.di.modules;

import com.edicasoft.journeyreport.works.SyncWorker;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WorkModule {
    @ContributesAndroidInjector
    abstract SyncWorker contributeSyncWorker();
}
