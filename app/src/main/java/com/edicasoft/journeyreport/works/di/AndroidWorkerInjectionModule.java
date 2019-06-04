package com.edicasoft.journeyreport.works.di;

import androidx.work.ListenableWorker;
import com.edicasoft.journeyreport.works.SyncWorker;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.Multibinds;
import java.util.Map;

@Module
public abstract class AndroidWorkerInjectionModule {

    @Binds
    abstract ListenableWorker bindSyncWorker(SyncWorker worker);

    @Multibinds
    abstract Map<Class<ListenableWorker>, AndroidInjector.Factory<ListenableWorker>>
    workerInjectorFactories();

}
