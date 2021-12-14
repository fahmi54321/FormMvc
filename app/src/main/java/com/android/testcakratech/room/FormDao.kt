package com.android.testcakratech.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.*

@Dao
interface FormDao {

    @Insert
    suspend fun insertForm(form: Form)

    @Query("SELECT * FROM form_table")
    fun getAllForm(): Single<List<Form>>

}