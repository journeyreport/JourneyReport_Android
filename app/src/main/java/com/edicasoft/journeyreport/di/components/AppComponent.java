package com.edicasoft.journeyreport.di.components;

import com.edicasoft.journeyreport.App;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Component(
    modules = {
        AndroidSupportInjectionModule.class,
    }
)
@Singleton
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    public interface Factory extends AndroidInjector.Factory<App> {

    }
}