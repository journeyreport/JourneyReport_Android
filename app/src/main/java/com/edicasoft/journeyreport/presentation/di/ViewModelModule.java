package com.edicasoft.journeyreport.presentation.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.edicasoft.journeyreport.di.keys.ViewModelKey;
import com.edicasoft.journeyreport.presentation.base.pm.FactoryViewModel;
import com.edicasoft.journeyreport.presentation.feature.splash.SplashViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);


    @Singleton
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}