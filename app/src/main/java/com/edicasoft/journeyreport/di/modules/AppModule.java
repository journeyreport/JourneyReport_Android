package com.edicasoft.journeyreport.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.edicasoft.journeyreport.App;
import com.edicasoft.journeyreport.BuildConfig;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = AppModule.PrefsModule.class)
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Context provideContext(App app);

    @Module
    public static class PrefsModule {
        @Provides
        SharedPreferences provideSharedPreferences(Context context) {
            return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        }
    }
}