package com.edicasoft.journeyreport.data.di;

import com.edicasoft.journeyreport.data.feature.configs.ConfigDataSource;
import com.edicasoft.journeyreport.data.feature.configs.PrefsConfigDataSource;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface DataSourceModule {

    @Singleton
    @Binds
    public ConfigDataSource provideConfigDataSource(PrefsConfigDataSource repository);
}
