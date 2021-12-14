package com.android.testcakratech.view.screens.form

import android.content.Context
import com.android.testcakratech.db.Form
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FormUseCase(
    private var dao: FormDao
) {

    sealed class Result {
        class Success() : Result()
        object Failure : Result()
    }

    suspend fun registerForm(
        nama: String,
        email: String,
        alamat: String,
    ): Result {
        return withContext(Dispatchers.IO) {
            try {
                var a = dao.insertForm(Form(0, nama, email, alamat))
                return@withContext Result.Success()
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

}