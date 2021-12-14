package com.android.testcakratech.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_table")
data class Form(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "form_id")
    var id: Int,

    @ColumnInfo(name = "form_nama")
    var nama: String,

    @ColumnInfo(name = "form_email")
    var email: String,

    @ColumnInfo(name = "form_alamat")
    var alamat: String
)