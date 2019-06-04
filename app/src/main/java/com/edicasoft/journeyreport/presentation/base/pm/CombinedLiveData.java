package com.edicasoft.journeyreport.presentation.base.pm;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.reactivex.functions.Function3;

public class CombinedLiveData<A, B, C, R> extends MediatorLiveData<R> {

    private A a0Value = null;
    private B b0Value = null;
    private C c0Value = null;
    private final Function3<A, B, C, R> combine;

    public CombinedLiveData(LiveData<A> t1LiveData, LiveData<B> t2LiveData,
        LiveData<C> t3LiveData, Function3<A, B, C, R> combine) {
        this.combine = combine;

        addSource(t1LiveData, t -> {
            a0Value = t;
            notifyOnChange();
        });

        addSource(t2LiveData, t -> {
            b0Value = t;
            notifyOnChange();
        });

        addSource(t3LiveData, t -> {
            c0Value = t;
            notifyOnChange();
        });
    }

    private void notifyOnChange() {
        try {
            postValue(combine.apply(a0Value, b0Value, c0Value));
        } catch (Exception e) {
            Log.e("CombinedLiveData", "notifyOnChange", e);
        }
    }


}
