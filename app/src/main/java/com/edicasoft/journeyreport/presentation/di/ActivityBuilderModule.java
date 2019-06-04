package com.edicasoft.journeyreport.presentation.di;

import com.edicasoft.journeyreport.MainActivity;
import com.edicasoft.journeyreport.presentation.feature.splash.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
