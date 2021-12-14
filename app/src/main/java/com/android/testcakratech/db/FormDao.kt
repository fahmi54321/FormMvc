package com.android.testcakratech.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface FormDao {

    @Insert
    fun insertForm(form: Form): Completable

    @Query("SELECT * FROM form_table")
    fun getAllForm(): Single<List<Form>>

}