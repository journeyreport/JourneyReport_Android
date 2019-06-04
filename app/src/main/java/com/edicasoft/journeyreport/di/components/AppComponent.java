package com.edicasoft.journeyreport.di.components;

import com.edicasoft.journeyreport.App;
import com.edicasoft.journeyreport.data.di.DataSourceModule;
import com.edicasoft.journeyreport.di.modules.ApiModule;
import com.edicasoft.journeyreport.di.modules.AppModule;
import com.edicasoft.journeyreport.di.modules.RepositoryModule;
import com.edicasoft.journeyreport.di.modules.WorkModule;
import com.edicasoft.journeyreport.presentation.di.PresentationModule;
import com.edicasoft.journeyreport.works.di.AndroidWorkerInjectionModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Component(
    modules = {
        AndroidSupportInjectionModule.class,
        AndroidWorkerInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        DataSourceModule.class,
        RepositoryModule.class,
        PresentationModule.class,
        WorkModule.class
    }
)
@Singleton
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory extends AndroidInjector.Factory<App> {

    }
}