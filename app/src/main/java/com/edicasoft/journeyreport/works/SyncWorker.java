package com.edicasoft.journeyreport.works;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SyncWorker extends Worker {

    /*
    @Inject
      SyncRepository syncRepository;
  */
    public SyncWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // syncRepository.sync().subscribe();
        return Result.success();
    }

    /*@Override
    public Single<Result> createWork() {
        return syncRepository.sync().andThen((SingleSource<Result>)
        observer -> observer.onSuccess(Result.success()));
    }*/
}
