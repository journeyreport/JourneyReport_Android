package com.edicasoft.journeyreport.presentation.di;

import dagger.Module;

@Module(includes = {ActivityBuilderModule.class, FragmentModule.class, ViewModelModule.class})
public abstract class PresentationModule {
}