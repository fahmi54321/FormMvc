package com.android.testcakratech.view.screens.main

import android.content.Context
import com.android.testcakratech.db.Form
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainUseCase(
    private val context: Context
) {
    //room
    private var dao = FormDatabase.getInstance(context.applicationContext).formDao()

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