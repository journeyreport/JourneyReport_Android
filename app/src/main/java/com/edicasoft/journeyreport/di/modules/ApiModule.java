package com.edicasoft.journeyreport.di.modules;

import com.edicasoft.journeyreport.data.DummyApi;
import com.edicasoft.journeyreport.network.api.ApiFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    DummyApi provideDummyApi() {
        return ApiFactory.create(DummyApi.class);
    }
}