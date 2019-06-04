package com.edicasoft.journeyreport.presentation.base.pm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val errorEvent = SingleLiveEvent<Throwable>()
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected inline fun addDisposable(block: () -> Disposable) = addDisposable(block.invoke())
}
