package com.edicasoft.journeyreport.works.di;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import java.lang.reflect.Constructor;

public class DaggerWorkerFactory extends WorkerFactory {

    @Nullable
    @Override
    public ListenableWorker createWorker(
        @NonNull Context appContext,
        @NonNull String workerClassName,
        @NonNull WorkerParameters workerParameters
    ) {
        try {
            final Constructor constructor = Class.forName(workerClassName)
                .asSubclass(Worker.class)
                .getDeclaredConstructor(Context.class, WorkerParameters.class);

            Worker worker = (Worker) constructor.newInstance(appContext, workerParameters);
            AndroidWorkerInjection.INSTANCE.inject(worker);
            return worker;
        } catch (Exception e) {
            Log.e("DaggerWorkerFactory", "exception", e);
        }
        return null;
    }
}
