package com.edicasoft.journeyreport.data.base;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class SchedulerTransformerUtil {

    private SchedulerTransformerUtil() {
    }

    public static <T> FlowableTransformer<T, T> applySchedulersIoToMain() {
        return observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
