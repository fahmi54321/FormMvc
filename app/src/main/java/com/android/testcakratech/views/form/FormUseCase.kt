package com.android.testcakratech.views.form

import com.android.testcakratech.room.Form
import com.android.testcakratech.room.FormDao
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
                dao.insertForm(Form(0, nama, email, alamat))
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