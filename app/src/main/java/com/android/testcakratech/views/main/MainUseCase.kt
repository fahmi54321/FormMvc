package com.android.testcakratech.views.main

import com.android.testcakratech.room.Form
import com.android.testcakratech.room.FormDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainUseCase(
    private var dao: FormDao
) {

    fun fetchData(
        responseHandler: (List<Form>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        dao.getAllForm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

}